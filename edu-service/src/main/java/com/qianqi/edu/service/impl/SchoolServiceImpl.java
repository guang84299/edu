package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.SchoolMapper;
import com.qianqi.edu.pojo.School;
import com.qianqi.edu.pojo.SchoolExample;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolMapper schoolMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SCHOOL_LIST}")
	private String SCHOOL_LIST; 
	
	@Override
	public void addSchool(School school) {
		schoolMapper.insertSelective(school);
	}

	@Override
	public void deleteSchool(Integer id) {
		schoolMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(SCHOOL_LIST, id+"");
	}

	@Override
	public void updateSchool(School school) {
		schoolMapper.updateByPrimaryKeySelective(school);
		jedisClient.hdel(SCHOOL_LIST, school.getId()+"");
	}

	@Override
	public School findSchool(Integer id) {
		String data = jedisClient.hget(SCHOOL_LIST,id+"");
		School school = null;
		if(StringUtils.isEmpty(data))
		{
			school = schoolMapper.selectByPrimaryKey(id);
			if(school != null)
			jedisClient.hset(SCHOOL_LIST, id+"", JsonUtils.objectToJson(school));
		}
		else
		{
			school = JsonUtils.jsonToPojo(data, School.class);
		}
		return school;
	}

	@Override
	public School findSchoolByName(String name) {
		SchoolExample schoolExample = new SchoolExample();
		schoolExample.createCriteria().andNameEqualTo(name);
		List<School> list = schoolMapper.selectByExample(schoolExample);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<School> findSchoolAll() {
		SchoolExample schoolExample = new SchoolExample();
		return schoolMapper.selectByExample(schoolExample);
	}

}
