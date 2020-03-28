package com.atguigu.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstReaml] doGetAuthenticationInfo");

		//1. 把AuthenticationToken强转为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		System.out.println("2..." + token.hashCode());

		//2. 从UsernamePasswordToken中获取username
		String username = upToken.getUsername();

		//3. 调用数据库的方法，从数据库中查询username对应的用户记录
		System.out.println("从数据库中获取username:" + username + "所对应的用户信息...");

		//4. 若用户不存在，则可以抛出UnknownAccountException异常
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在！");
		}

		//5. 根据用户信息的情况, 决定是否需要抛出其他的AuthenticationException异常
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁定！");
		}

		//6. 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为：SimpleAuthenticationInfo

		//以下信息是从数据库中获取的信息
		//1). principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象
		Object principal = username;
		//2). credentials：密码，从数据库中查询出来
		Object credentials = null;
		if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		//3). realmName：当前realm对象的name，调用父类的getName()方法即可获取
		String realmName = getName();
		//4). 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);

		SimpleAuthenticationInfo info;//new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

		//upToken封装了前端的用户名和密码信息，info封装了从数据库中查询到的用户名和密码信息
		//由Shiro完成密码比对
		//md5盐值加密，可以确保两个人密码一样但最终保存到数据库中两个人密码的字符串却不一样，这样更安全
		//一般用什么来作为盐呢？用一个唯一的字符串。。。

		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";//哈希算法名称，MD5加密算法其实是一种哈希算法，还有sha算法家族也是哈希算法的一种
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

	//授权会被shiro回调的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//1. 从PrincipalCollection中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();

		//2. 利用登录的用户的信息来用户当前用户的角色或权限（可能需要查询数据库）
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}

		//3. 创建SimpleAuthorizationInfo，并设置其reles属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		//4. 返回SimpleAuthorizationInfo对象
		return info;
	}

}
