package com.qianqi.edu.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.qianqi.edu.mapper.UserMapper;
import com.qianqi.edu.pojo.User;
import com.qianqi.edu.pojo.UserExample;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.SsoService;

@Service
public class SsoServiceImpl implements SsoService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_LIST}")
	private String SESSION_LIST; 
	
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Override
	public EduResult userLogin(String name, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(name)
		.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<User> list = userMapper.selectByExample(userExample);
		if(list != null && list.size()>0)
		{
			String token = UUID.randomUUID().toString();
			User user = list.get(0);
			user.setPassword(null);
			jedisClient.set(SESSION_LIST+token, JsonUtils.objectToJson(user));
			// 设置Session的过期时间
			jedisClient.expire(SESSION_LIST+token, SESSION_EXPIRE);
			return EduResult.ok(null, token);
		}
		return EduResult.err(null, null);
	}
	
	@Override
	public EduResult getUserByToken(String token) {
		String data = jedisClient.get(SESSION_LIST+token);
		if(StringUtils.isEmpty(data))
		{
			return EduResult.err(null, null);
		}
		jedisClient.expire(SESSION_LIST+token, SESSION_EXPIRE);
		User user = JsonUtils.jsonToPojo(data, User.class);
		return EduResult.ok(null, user);
	}

	@Override
	public EduResult userLoginOut(String token) {
		jedisClient.del(SESSION_LIST+token);
		return EduResult.ok(null, null);
	}

}
