package com.atguigu.shiro.handlers;

import com.atguigu.shiro.services.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

	@Autowired
	private ShiroService shiroService;

	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session,Integer userId) /*throws SystemException */{

//		if(userId == null){
//			throw new SystemException(Config.INVALIDATION,"用户名不能为空");
//		}

		session.setAttribute("key", "value123456");
		shiroService.testMethod();
		return "redirect:/login.jsp";
	}

	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password){
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			// 若没有认证，则把用户名和密码封装成UsernamePasswordToken类的对象实例
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// 记住我，原理是在客户端生成了一个Cookie
			token.setRememberMe(true);
			try {
				System.out.println("1..." + token.hashCode());
				// 执行登录
				currentUser.login(token);
			}
			// ... catch more exceptions here (maybe custom ones specific to your application?
			// 所有认证时出现的异常的父类
			catch (AuthenticationException ae) {
				// unexpected condition?  error?
			}
		}

		return "redirect:/list.jsp";
	}

}
