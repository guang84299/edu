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
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${TEACHER_LIST}")
	private String TEACHER_LIST; 
	
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

}
