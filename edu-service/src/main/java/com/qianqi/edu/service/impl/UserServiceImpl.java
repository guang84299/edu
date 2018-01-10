package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.UserMapper;
import com.qianqi.edu.pojo.User;
import com.qianqi.edu.pojo.UserExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${USER_LIST}")
	private String USER_LIST; 
	
	@Override
	public User findUserById(Long id) {
		String data = jedisClient.hget(USER_LIST,id+"");
		User user = null;
		if(StringUtils.isEmpty(data))
		{
			user = userMapper.selectByPrimaryKey(id);
			jedisClient.hset(USER_LIST, id+"", JsonUtils.objectToJson(user));
		}
		else
		{
			user = JsonUtils.jsonToPojo(data, User.class);
		}
		return user;
	}
	@Override
	public void addUser(User user) {
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password);
		userMapper.insertSelective(user);
	}
	@Override
	public EasyUIDataGridResult findUserList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<User> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	
	@Override
	public void updateUser(User user) {
		User u = findUserById(user.getId());
		//判断密码是否改变
		if(!u.getPassword().equals(user.getPassword()))
		{
			String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
			user.setPassword(password);
		}
		userMapper.updateByPrimaryKeySelective(user);
		jedisClient.hdel(USER_LIST, user.getId()+"");
	}
	
	@Override
	public void deleteUser(Long id) {
		userMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(USER_LIST, id+"");
	}

}
