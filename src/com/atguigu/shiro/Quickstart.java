package com.atguigu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        // 获取当前的Subject，调用SecurityUtils.getSubject()方法
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        // 测试使用Session
        // 获取Session，调用Subject的getSession()方法
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("--->Retrieved the correct value! [" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        // 测试当前用户是否已经被认证，即是否已经登录
        // 调用Subject的isAuthenticated()方法
        if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装成UsernamePasswordToken类的对象实例
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // 记住我
            token.setRememberMe(true);
            try {
            	// ִ调用Subject的login()方法执行登录，能不能登录成功取决于shiro.ini配置文件是否配置了lonestarr/vespa
                currentUser.login(token);
            }
            // 若没有指定的账户，shiro将会抛出UnknownAccountException
            catch (UnknownAccountException uae) {
                log.info("---->There is no user with username of " + token.getPrincipal());
                return;
            }
            // 若账户存在但密码不匹配，shiro将会抛出IncorrectCredentialsException
            catch (IncorrectCredentialsException ice) {
                log.info("---->Password for account " + token.getPrincipal() + " was incorrect!");
                return;
            }
            // 账户被锁定，shiro将会抛出LockedAccountException
            catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时出现的异常的父类
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("----> User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        //测试当前账户是否被授予了某一个角色
        if (currentUser.hasRole("schwartz")) {
            log.info("---->May the Schwartz be with you!");
        } else {
            log.info("---->Hello, mere mortal.");
            return;
        }

        //test a typed permission (not instance-level)
        //测试当前账户是否具备某一个行为，调用Subject的isPermitted()方法
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("---->You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        //测试当前账户是否具备某一个行为
        if (currentUser.isPermitted("user:delete:zhangsan")) {
            log.info("----> You are permitted to 'delete' the user with license plate (id) 'zhangsan'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to delete the 'zhangsan' user!");
        }

        //all done - log out!
        System.out.println("---->" + currentUser.isAuthenticated());//true

        //注销，退出登录
        currentUser.logout();
        
        System.out.println("---->" + currentUser.isAuthenticated());//false

        System.exit(0);
    }

}
