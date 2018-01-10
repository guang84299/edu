package com.qianqi.edu.laboratory.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianqi.edu.pojo.User;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.service.SsoService;
import com.qianqi.edu.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SsoService ssoService;
	@RequestMapping("/")
	public String showIndex()
	{
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin()
	{
		return "login";
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public EduResult userLogin(String name,String password)
	{
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(name))
			return EduResult.err("null", null); 
		return ssoService.userLogin(name, password);
	}
	
	@RequestMapping("/user/loginout")
	@ResponseBody
	public EduResult userLoginOut(String token)
	{
		if(StringUtils.isEmpty(token))
			return EduResult.err("null", null); 
		return ssoService.userLoginOut(token);
	}
	
	@RequestMapping("/user/toadd")
	public String toAddUser()
	{
		return "user-add";
	}
	
	@RequestMapping("/user/add")
	@ResponseBody
	public EduResult addUser(@RequestBody User user)
	{
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setStatus(0);
		userService.addUser(user);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/tolist")
	public String toUserList()
	{
		return "user-list";
	}
	
	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUIDataGridResult userList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = userService.findUserList(page, rows);
		return result;
	}
	
	@RequestMapping("/user/toedit")
	public String toEditUser()
	{
		return "user-edit";
	}
	
	@RequestMapping("/user/edit")
	@ResponseBody
	public EduResult editUser(@RequestBody User user)
	{
		user.setUpdated(new Date());
		userService.updateUser(user);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/delete")
	@ResponseBody
	public EduResult deleteUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			userService.deleteUser(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/disable")
	@ResponseBody
	public EduResult disableUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			User u = userService.findUserById(id);
			u.setStatus(-1);
			u.setUpdated(new Date());
			userService.updateUser(u);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/enable")
	@ResponseBody
	public EduResult enableUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			User u = userService.findUserById(id);
			u.setStatus(0);
			u.setUpdated(new Date());
			userService.updateUser(u);
		}
		return EduResult.ok("", null);
	}
}
