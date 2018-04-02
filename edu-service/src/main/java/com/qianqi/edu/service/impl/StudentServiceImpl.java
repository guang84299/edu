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
import com.qianqi.edu.mapper.StudentTclassMapper;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentExample;
import com.qianqi.edu.pojo.StudentTclass;
import com.qianqi.edu.pojo.StudentTclassExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private StudentTclassMapper studentTclassMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${STUDENT_LIST}")
	private String STUDENT_LIST; 
	
	@Value("${STUDENT_TCLASS_LIST}")
	private String STUDENT_TCLASS_LIST; 
	
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
	public void addStudentTclass(StudentTclass studentTclass) {
		studentTclassMapper.insertSelective(studentTclass);
	}

	@Override
	public void deleteStudentTclass(Long id) {
		studentTclassMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(STUDENT_TCLASS_LIST, id+"");
	}

	@Override
	public void updateStudentTclass(StudentTclass studentTclass) {
		studentTclassMapper.updateByPrimaryKeySelective(studentTclass);
		jedisClient.hdel(STUDENT_TCLASS_LIST, studentTclass.getId()+"");
	}

	@Override
	public StudentTclass findStudentTclassById(Long id) {
		String data = jedisClient.hget(STUDENT_TCLASS_LIST,id+"");
		StudentTclass studentTclass = null;
		if(StringUtils.isEmpty(data))
		{
			studentTclass = studentTclassMapper.selectByPrimaryKey(id);
			if(studentTclass != null)
			jedisClient.hset(STUDENT_TCLASS_LIST, id+"", JsonUtils.objectToJson(studentTclass));
		}
		else
		{
			studentTclass = JsonUtils.jsonToPojo(data, StudentTclass.class);
		}
		return studentTclass;
	}

	@Override
	public List<StudentTclass> findStudentTclassByStudentId(Long studentId) {
		StudentTclassExample example = new StudentTclassExample();
		example.createCriteria().andStudentIdEqualTo(studentId);
		
		return studentTclassMapper.selectByExample(example);
	}

	@Override
	public List<StudentTclass> findStudentTclassByTclassId(Long tclassId) {
		StudentTclassExample example = new StudentTclassExample();
		example.createCriteria().andTclassIdEqualTo(tclassId);
		
		return studentTclassMapper.selectByExample(example);
	}

	@Override
	public EasyUIDataGridResult findStudentTclassListByStudentId(Long studentId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTclassExample example = new StudentTclassExample();
		example.createCriteria().andStudentIdEqualTo(studentId);
		List<StudentTclass> list = studentTclassMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTclass> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findStudentTclassListByTclassId(Long tclassId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTclassExample example = new StudentTclassExample();
		example.createCriteria().andTclassIdEqualTo(tclassId);
		List<StudentTclass> list = studentTclassMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTclass> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findStudentTclassListByTclassIds(List<Long> tclassIds, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		StudentTclassExample example = new StudentTclassExample();
		example.createCriteria().andTclassIdIn(tclassIds);
		List<StudentTclass> list = studentTclassMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<StudentTclass> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

}
