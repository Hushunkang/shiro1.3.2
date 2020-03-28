package com.atguigu.shiro.factory;

import java.util.LinkedHashMap;
import java.util.Map;

public class FilterChainDefinitionMapBuilder {

	public Map<String, String> buildFilterChainDefinitionMap(){
		Map<String, String> map = new LinkedHashMap<>();

		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/admin.jsp", "authc,roles[admin]");
        map.put("/user.jsp", "authc,roles[user]");
//        map.put("/list.jsp", "admin");
		map.put("/**", "authc");

		return map;
	}
	
}
