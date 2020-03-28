package com.atguigu.shiro.factory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用实例工厂方法来配置bean：需要事先创建工厂本身的对象实例，然后再调用实例工厂方法
 */
public class FilterChainDefinitionMapBuilder {

	/**
	 * 实例工厂方法
	 * @return
	 */
	public Map<String, String> buildFilterChainDefinitionMap(){
		Map<String, String> map = new LinkedHashMap<>();

		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/admin.jsp", "authc,roles[admin]");//用户必须要认证并且有admin角色才可以访问的页面
        map.put("/user.jsp", "authc,roles[user]");//用户必须要认证并且有user角色才可以访问的页面
        map.put("/list.jsp", "admin");//用户admin可以通过记住我就可以访问list.jsp
        map.put("/list.jsp", "user");//用户user可以通过记住我就可以访问list.jsp
		map.put("/**", "authc");

		return map;
	}
	
}
