package com.qianqi.edu.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.qianqi.edu.mapper.StudentMapper;
import com.qianqi.edu.mapper.TeacherMapper;
import com.qianqi.edu.mapper.UserMapper;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentExample;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherExample;
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
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_LIST}")
	private String SESSION_LIST; 
	
	@Value("${SESSION_TEACHER_LIST}")
	private String SESSION_TEACHER_LIST; 
	
	@Value("${SESSION_STUDENT_LIST}")
	private String SESSION_STUDENT_LIST;
	
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

	@Override
	public EduResult teacherLogin(String phone, String password) {
		TeacherExample teacherExample = new TeacherExample();
		teacherExample.createCriteria().andPhoneEqualTo(phone)
		.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<Teacher> list = teacherMapper.selectByExample(teacherExample);
		if(list != null && list.size()>0)
		{
			String token = UUID.randomUUID().toString();
			Teacher teacher = list.get(0);
			if(teacher.getState() == 0)
				return EduResult.err("账号未审核",null);
			if(teacher.getState() == 3)
				return EduResult.err("审核不通过",null);
			if(teacher.getState() != 1)
				return EduResult.err("账号不合法",null);
			
			teacher.setPassword(null);
			jedisClient.set(SESSION_TEACHER_LIST+token, JsonUtils.objectToJson(teacher));
			// 设置Session的过期时间
			jedisClient.expire(SESSION_TEACHER_LIST+token, SESSION_EXPIRE);
			return EduResult.ok(null, token);
		}
		return EduResult.err(null, null);
	}

	@Override
	public EduResult getTeacherByToken(String token) {
		String data = jedisClient.get(SESSION_TEACHER_LIST+token);
		if(StringUtils.isEmpty(data))
		{
			return EduResult.err(null, null);
		}
		jedisClient.expire(SESSION_TEACHER_LIST+token, SESSION_EXPIRE);
		Teacher teacher = JsonUtils.jsonToPojo(data, Teacher.class);
		return EduResult.ok(null, teacher);
	}

	@Override
	public EduResult teacherLoginOut(String token) {
		jedisClient.del(SESSION_TEACHER_LIST+token);
		return EduResult.ok(null, null);
	}

	@Override
	public EduResult studentLogin(String phone, String password) {
		StudentExample studentExample = new StudentExample();
		studentExample.createCriteria().andPhoneEqualTo(phone)
		.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<Student> list = studentMapper.selectByExample(studentExample);
		if(list != null && list.size()>0)
		{
			String token = UUID.randomUUID().toString();
			Student student = list.get(0);
			student.setPassword(null);
			String students = JsonUtils.objectToJson(student);
			jedisClient.set(SESSION_STUDENT_LIST+token, students);
			// 设置Session的过期时间
			jedisClient.expire(SESSION_STUDENT_LIST+token, SESSION_EXPIRE);
			return EduResult.ok(students, token);
		}
		return EduResult.err(null, null);
	}

	@Override
	public EduResult getStudentByToken(String token) {
		String data = jedisClient.get(SESSION_STUDENT_LIST+token);
		if(StringUtils.isEmpty(data))
		{
			return EduResult.err(null, null);
		}
		jedisClient.expire(SESSION_STUDENT_LIST+token, SESSION_EXPIRE);
		Student student = JsonUtils.jsonToPojo(data, Student.class);
		return EduResult.ok(null, student);
	}

	@Override
	public EduResult studentLoginOut(String token) {
		jedisClient.del(SESSION_STUDENT_LIST+token);
		return EduResult.ok(null, null);
	}

}
