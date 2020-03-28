package com.atguigu.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

//	@Autowired
//	private ShiroService shiroService;
//
//	@RequestMapping("/testShiroAnnotation")
//	public String testShiroAnnotation(HttpSession session){
//		session.setAttribute("key", "value12345");
//		shiroService.testMethod();
//		return "redirect:/list.jsp";
//	}

	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password){
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			// 若没有认证，则把用户名和密码封装成UsernamePasswordToken类的对象实例
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// 记住我
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
