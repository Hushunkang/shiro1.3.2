package com.atguigu.shiro.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

public class ShiroService {
	
	@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("testMethod,time:" + new Date());

		Session session = SecurityUtils.getSubject().getSession();
		//注意：这个Session是shiro中提供的API，不是JavaWEB中的Servlet API
		//若这个API是Servlet API便不能写在Service层，否则就是侵入式的设计，Servlet API规范的用法是在控制层使用而不能在Service层、Dao层使用
		//在控制层建议还是使用最原生的Servlet API里面的那个Session，那么介绍shiro中的Session还有什么意义呢？
		//因为用shiro中的Session我可以在Service层用这个类型的Session，而使用原生的Servlet API里面的那个Session不建议在Service层使用，因为侵入式设计
		//未验证：个人觉得Shiro中的Session应该是对原生的Servlet API里面的Session做了兼容
		//导致不管在javase还是在javaee环境下，shiro都会有session
		Object val = session.getAttribute("key");

		System.out.println("Service SessionVal: " + val);

		//javaee环境下，这个获取到的就是sessionId，即一个客户端与一个web app关联的会话标识
		System.out.println(session.getId());//B66ADCBD485080189EEE6ADEEFD4B2EA
	}
	
}
 