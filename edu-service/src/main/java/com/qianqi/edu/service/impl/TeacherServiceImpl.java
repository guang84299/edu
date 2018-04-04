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
import com.qianqi.edu.mapper.TeacherMapper;
import com.qianqi.edu.mapper.TeacherSubjectMapper;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherExample;
import com.qianqi.edu.pojo.TeacherExample.Criteria;
import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.TeacherSubjectExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TeacherSubjectMapper teacherSubjectMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${TEACHER_LIST}")
	private String TEACHER_LIST; 
	
	@Value("${TEACHER_SUBJECT_LIST}")
	private String TEACHER_SUBJECT_LIST; 
	
	@Value("${TEACHER_SUBJECT_ID}")
	private String TEACHER_SUBJECT_ID; 
	
	@Value("${TEACHER_SUBJECT_NUM}")
	private String TEACHER_SUBJECT_NUM; 
	
	@Override
	public Teacher findTeacherById(Long id) {
		String data = jedisClient.hget(TEACHER_LIST,id+"");
		Teacher teacher = null;
		if(StringUtils.isEmpty(data))
		{
			teacher = teacherMapper.selectByPrimaryKey(id);
			if(teacher != null)
			jedisClient.hset(TEACHER_LIST, id+"", JsonUtils.objectToJson(teacher));
		}
		else
		{
			teacher = JsonUtils.jsonToPojo(data, Teacher.class);
		}
		return teacher;
	}

	@Override
	public void addTeacher(Teacher teacher) {
		String password = DigestUtils.md5DigestAsHex(teacher.getPassword().getBytes());
		teacher.setPassword(password);
		teacherMapper.insertSelective(teacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		Teacher t = findTeacherById(teacher.getId());
		//判断密码是否改变
		if(!t.getPassword().equals(teacher.getPassword()))
		{
			String password = DigestUtils.md5DigestAsHex(teacher.getPassword().getBytes());
			teacher.setPassword(password);
		}
		teacherMapper.updateByPrimaryKeySelective(teacher);
		jedisClient.hdel(TEACHER_LIST, teacher.getId()+"");
	}

	@Override
	public void deleteTeacher(Long id) {
		teacherMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(TEACHER_LIST, id+"");
	}

	@Override
	public EasyUIDataGridResult findTeacherList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TeacherExample example = new TeacherExample();
		List<Teacher> list = teacherMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Teacher> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	
	
	@Override
	public EasyUIDataGridResult findTeacherList(List<Integer> schools,List<Integer> gradeIds,List<Integer> subjectIds,int page,int rows)
	{
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TeacherExample example = new TeacherExample();
		example.setOrderByClause("state asc");
		Criteria criteria = example.createCriteria();
		if(schools != null && schools.size()>0)
			criteria = criteria.andSchoolIdIn(schools);
		if(gradeIds != null && gradeIds.size()>0)
			criteria = criteria.andGradeIdIn(gradeIds);
		if(subjectIds != null && subjectIds.size()>0)
			criteria = criteria.andSubjectIdIn(subjectIds);
		
		List<Teacher> list = teacherMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Teacher> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	
	
	//--------------2--------------
	
	
	
	@Override
	public void addTeacherSubject(TeacherSubject teacherSubject) {
		if(!jedisClient.exists(TEACHER_SUBJECT_ID))
		{
			jedisClient.set(TEACHER_SUBJECT_ID, TEACHER_SUBJECT_NUM);
		}
		jedisClient.incr(TEACHER_SUBJECT_ID);
		String num = jedisClient.get(TEACHER_SUBJECT_ID);
		long id = Long.parseLong(num);
		teacherSubject.setId(id);
		teacherSubjectMapper.insertSelective(teacherSubject);
	}

	@Override
	public void deleteTeacherSubject(Long id) {
		teacherSubjectMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(TEACHER_SUBJECT_LIST, id+"");
	}

	@Override
	public void updateTeacherSubject(TeacherSubject teacherSubject) {
		teacherSubjectMapper.updateByPrimaryKeySelective(teacherSubject);
		jedisClient.hdel(TEACHER_SUBJECT_LIST, teacherSubject.getId()+"");
	}

	@Override
	public TeacherSubject findTeacherSubject(Long id) {
		String data = jedisClient.hget(TEACHER_SUBJECT_LIST,id+"");
		TeacherSubject teacherSubject = null;
		if(StringUtils.isEmpty(data))
		{
			teacherSubject = teacherSubjectMapper.selectByPrimaryKey(id);
			if(teacherSubject != null)
				jedisClient.hset(TEACHER_SUBJECT_LIST, id+"", JsonUtils.objectToJson(teacherSubject));
		}
		else
		{
			teacherSubject = JsonUtils.jsonToPojo(data, TeacherSubject.class);
		}
		return teacherSubject;
	}

	@Override
	public List<TeacherSubject> findTeacherSubjectByTeacherId(Long teacherId) {
		TeacherSubjectExample example = new TeacherSubjectExample();
		example.createCriteria().andTeacherIdEqualTo(teacherId);
		return teacherSubjectMapper.selectByExample(example);
	}

	@Override
	public EasyUIDataGridResult findTeacherSubjectList(int page, int rows) {
		return findTeacherSubjectList(null,page,rows);
	}

	@Override
	public EasyUIDataGridResult findTeacherSubjectList(Long teacherId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TeacherSubjectExample example = new TeacherSubjectExample();
		if(teacherId != null)
			example.createCriteria().andTeacherIdEqualTo(teacherId);
		List<TeacherSubject> list = teacherSubjectMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TeacherSubject> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

}
