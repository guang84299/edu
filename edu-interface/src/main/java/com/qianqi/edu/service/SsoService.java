package com.qianqi.edu.service;

import com.qianqi.edu.pojo.common.EduResult;

public interface SsoService {
	EduResult userLogin(String name,String password);
	EduResult getUserByToken(String token);
	EduResult userLoginOut(String token);
	
	EduResult teacherLogin(String phone,String password);
	EduResult getTeacherByToken(String token);
	EduResult teacherLoginOut(String token);
	
	EduResult studentLogin(String phone,String password);
	EduResult getStudentByToken(String token);
	EduResult studentLoginOut(String token);
}
