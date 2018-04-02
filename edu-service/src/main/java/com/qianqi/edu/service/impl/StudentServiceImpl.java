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
import com.qianqi.edu.mapper.StudentMapper;
import com.qianqi.edu.mapper.StudentTeacherSubjectMapper;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentExample;
import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.StudentTeacherSubjectExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private StudentTeacherSubjectMapper studentTeacherSubjectMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${STUDENT_LIST}")
	private String STUDENT_LIST; 
	
	@Value("${STUDENT_TEACHER_SUBJECT_LIST}")
	private String STUDENT_TEACHER_SUBJECT_LIST; 
	
	@Override
	public void addStudent(Student student) {
		String password = DigestUtils.md5DigestAsHex(student.getPassword().getBytes());
		student.setPassword(password);
		studentMapper.insert(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(STUDENT_LIST, id+"");
	}

	@Override
	public void updateStudent(Student student) {
		Student s = findStudentById(student.getId());
		//判断密码是否改变
		if(!s.getPassword().equals(student.getPassword()))
		{
			String password = DigestUtils.md5DigestAsHex(student.getPassword().getBytes());
			student.setPassword(password);
		}		
		studentMapper.updateByPrimaryKeySelective(student);
		jedisClient.hdel(STUDENT_LIST, student.getId()+"");
	}

	@Override
	public Student findStudentById(Long id) {
		String data = jedisClient.hget(STUDENT_LIST,id+"");
		Student student = null;
		if(StringUtils.isEmpty(data))
		{
			student = studentMapper.selectByPrimaryKey(id);
			if(student != null)
			jedisClient.hset(STUDENT_LIST, id+"", JsonUtils.objectToJson(student));
		}
		else
		{
			student = JsonUtils.jsonToPojo(data, Student.class);
		}
		return student;
	}

	@Override
	public Student findStudentByPhone(String phone) {
		StudentExample studentExample = new StudentExample();
		studentExample.createCriteria().andPhoneEqualTo(phone);
		List<Student> list = studentMapper.selectByExample(studentExample);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public EasyUIDataGridResult findStudentList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentExample example = new StudentExample();
		List<Student> list = studentMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Student> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	
	
	//--------------------------StudentTclass----------------



	@Override
	public void addStudentTeacherSubject(StudentTeacherSubject studentTeacherSubject) {
		studentTeacherSubjectMapper.insertSelective(studentTeacherSubject);
	}

	@Override
	public void deleteStudentTeacherSubject(Long id) {
		studentTeacherSubjectMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(STUDENT_TEACHER_SUBJECT_LIST, id+"");
	}

	@Override
	public void updateStudentTeacherSubject(StudentTeacherSubject studentTeacherSubject) {
		studentTeacherSubjectMapper.updateByPrimaryKey(studentTeacherSubject);
	}

	@Override
	public StudentTeacherSubject findStudentTeacherSubjectById(Long id) {
		String data = jedisClient.hget(STUDENT_TEACHER_SUBJECT_LIST,id+"");
		StudentTeacherSubject studentTeacherSubject = null;
		if(StringUtils.isEmpty(data))
		{
			studentTeacherSubject =studentTeacherSubjectMapper.selectByPrimaryKey(id);
			if(studentTeacherSubject != null)
			jedisClient.hset(STUDENT_TEACHER_SUBJECT_LIST, id+"", JsonUtils.objectToJson(studentTeacherSubject));
		}
		else
		{
			studentTeacherSubject = JsonUtils.jsonToPojo(data, StudentTeacherSubject.class);
		}
		return studentTeacherSubject;
	}

	@Override
	public List<StudentTeacherSubject> findStudentTeacherSubjectByStudentId(Long studentId) {
		StudentTeacherSubjectExample example = new StudentTeacherSubjectExample();
		example.createCriteria().andStudentIdEqualTo(studentId);
		
		return studentTeacherSubjectMapper.selectByExample(example);
	}

	@Override
	public List<StudentTeacherSubject> findStudentTeacherSubjectByTeacherSubjectId(Long teacherSubjectId) {
		StudentTeacherSubjectExample example = new StudentTeacherSubjectExample();
		example.createCriteria().andTeacherSubjectIdEqualTo(teacherSubjectId);
		
		return studentTeacherSubjectMapper.selectByExample(example);
	}

	@Override
	public EasyUIDataGridResult findStudentTeacherSubjectListByStudentId(Long studentId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTeacherSubjectExample example = new StudentTeacherSubjectExample();
		example.createCriteria().andStudentIdEqualTo(studentId);
		List<StudentTeacherSubject> list = studentTeacherSubjectMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTeacherSubject> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findStudentTeacherSubjectListByTeacherSubjectId(Long teacherSubjectId, int page,
			int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTeacherSubjectExample example = new StudentTeacherSubjectExample();
		example.createCriteria().andTeacherSubjectIdEqualTo(teacherSubjectId);
		List<StudentTeacherSubject> list = studentTeacherSubjectMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTeacherSubject> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findStudentTeacherSubjectListByTeacherSubjectIds(List<Long> teacherSubjectIds, int page,
			int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTeacherSubjectExample example = new StudentTeacherSubjectExample();
		example.createCriteria().andTeacherSubjectIdIn(teacherSubjectIds);
		List<StudentTeacherSubject> list = studentTeacherSubjectMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTeacherSubject> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

}
