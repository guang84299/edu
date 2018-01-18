package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.SubjectMapper;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.SubjectExample;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SUBJECT_LIST}")
	private String SUBJECT_LIST; 
	
	@Override
	public void addSubject(Subject subject) {
		subjectMapper.insertSelective(subject);
	}

	@Override
	public void deleteSubject(Integer id) {
		subjectMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(SUBJECT_LIST, id+"");
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectMapper.updateByPrimaryKeySelective(subject);
		jedisClient.hdel(SUBJECT_LIST, subject.getId()+"");
	}

	@Override
	public Subject findSubject(Integer id) {
		String data = jedisClient.hget(SUBJECT_LIST,id+"");
		Subject subject = null;
		if(StringUtils.isEmpty(data))
		{
			subject = subjectMapper.selectByPrimaryKey(id);
			if(subject != null)
			jedisClient.hset(SUBJECT_LIST, id+"", JsonUtils.objectToJson(subject));
		}
		else
		{
			subject = JsonUtils.jsonToPojo(data, Subject.class);
		}
		return subject;
	}

	@Override
	public Subject findSubjectByName(String name) {
		SubjectExample subjectExample = new SubjectExample();
		subjectExample.createCriteria().andNameEqualTo(name);
		List<Subject> list = subjectMapper.selectByExample(subjectExample);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Subject> findSubjectAll() {
		SubjectExample subjectExample = new SubjectExample();
		return subjectMapper.selectByExample(subjectExample);
	}

}
