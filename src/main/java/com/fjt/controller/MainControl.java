package com.fjt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.entitys.User;
import com.fjt.service.UserService;

/**
 * 主界面控制器
 * @author 1
 *
 */
@Controller
public class MainControl {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 访问主界面
	 * @param map
	 * @return
	 */
	@RequestMapping("logMain")
	public String logMain(@RequestParam Map<String, String > map)
	{
		String username=map.get("user");
		String password=map.get("password");
		
	    User user=userService.findUser(username,password);
		if(user==null){
			return "login";
		}
		
		return "main";
	}
	
	
 
  
	/**
	 * 退出主界面
	 * @return
	 */
	@RequestMapping("exitInterface")
	public String exitInterface(){
		return "index";
	}
}
