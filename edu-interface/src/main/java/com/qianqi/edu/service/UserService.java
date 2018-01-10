package com.qianqi.edu.service;


import com.qianqi.edu.pojo.User;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface UserService {

	User findUserById(Long id);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(Long id);
	
	EasyUIDataGridResult findUserList(int page,int rows);
}
