package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.GradeMapper;
import com.qianqi.edu.pojo.Grade;
import com.qianqi.edu.pojo.GradeExample;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.JedisClient;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${GRADE_LIST}")
	private String GRADE_LIST; 
	
	@Override
	public void addGrade(Grade grade) {
		gradeMapper.insertSelective(grade);
	}

	@Override
	public void deleteGrade(Integer id) {
		gradeMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(GRADE_LIST, id+"");
	}

	@Override
	public void updateGrade(Grade grade) {
		gradeMapper.updateByPrimaryKeySelective(grade);
		jedisClient.hdel(GRADE_LIST, grade.getId()+"");
	}

	@Override
	public Grade findGrade(Integer id) {
		String data = jedisClient.hget(GRADE_LIST,id+"");
		Grade grade = null;
		if(StringUtils.isEmpty(data))
		{
			grade = gradeMapper.selectByPrimaryKey(id);
			if(grade != null)
			jedisClient.hset(GRADE_LIST, id+"", JsonUtils.objectToJson(grade));
		}
		else
		{
			grade = JsonUtils.jsonToPojo(data, Grade.class);
		}
		return grade;
	}

	@Override
	public Grade findGradeByName(String name) {
		GradeExample gradeExample = new GradeExample();
		gradeExample.createCriteria().andNameEqualTo(name);
		List<Grade> list = gradeMapper.selectByExample(gradeExample);
		if(list!=null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Grade> findGradeAll() {
		GradeExample gradeExample = new GradeExample();
		return gradeMapper.selectByExample(gradeExample);
	}

}
