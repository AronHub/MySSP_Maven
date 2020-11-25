package com.fjt.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjt.entitys.User;
import com.fjt.service.UserService;
//登录控制器
@Controller
public class LogControl{

	@Autowired
	private UserService userService;
	
	

	//访问登录界面
	@RequestMapping("/")
	public String jumpLogin(){
		return "login";
	}
	

	
	
	
}
