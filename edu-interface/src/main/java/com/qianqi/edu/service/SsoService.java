package com.qianqi.edu.service;

import com.qianqi.edu.pojo.common.EduResult;

public interface SsoService {
	EduResult userLogin(String name,String password);
	EduResult getUserByToken(String token);
	EduResult userLoginOut(String token);
}
