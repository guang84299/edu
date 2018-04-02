package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.KnowledgeMapper;
import com.qianqi.edu.pojo.Knowledge;
import com.qianqi.edu.pojo.KnowledgeExample;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.KnowledgeService;

@Service     
public class KnowledgeServiceImpl implements KnowledgeService{
			 
	@Autowired
	private KnowledgeMapper knowledgeMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${KNOWLEDGE_LIST}")
	private String KNOWLEDGE_LIST; 
	
	@Value("${KNOWLEDGE_ID}")
	private String KNOWLEDGE_ID; 
	
	@Value("${KNOWLEDGE_NUM}")
	private String KNOWLEDGE_NUM; 
	
	@Override
	public long addKnowledge(Knowledge knowledge) {
		if(!jedisClient.exists(KNOWLEDGE_ID))
		{
			jedisClient.set(KNOWLEDGE_ID, KNOWLEDGE_NUM);
		}
		jedisClient.incr(KNOWLEDGE_ID);
		String num = jedisClient.get(KNOWLEDGE_ID);
		long id = Long.parseLong(num);
		knowledge.setId(id);
		
		knowledgeMapper.insertSelective(knowledge);
		return id;
	}

	@Override
	public void deleteKnowledge(Long id) {
		knowledgeMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(KNOWLEDGE_LIST, id+"");
	}

	@Override
	public void updateKnowledge(Knowledge knowledge) {
		knowledgeMapper.updateByPrimaryKeySelective(knowledge);
		jedisClient.hdel(KNOWLEDGE_LIST, knowledge.getId()+"");
	}

	@Override
	public Knowledge findKnowledge(Long id) {
		String data = jedisClient.hget(KNOWLEDGE_LIST,id+"");
		Knowledge knowledge = null;
		if(StringUtils.isEmpty(data))
		{
			knowledge = knowledgeMapper.selectByPrimaryKey(id);
			if(knowledge != null)
			jedisClient.hset(KNOWLEDGE_LIST, id+"", JsonUtils.objectToJson(knowledge));
		}
		else
		{
			knowledge = JsonUtils.jsonToPojo(data, Knowledge.class);
		}
		return knowledge;
	}


	@Override
	public List<Knowledge> findKnowledgeAll() {
		KnowledgeExample knowledgeExample = new KnowledgeExample();
		return knowledgeMapper.selectByExample(knowledgeExample);
	}

	

	@Override
	public Knowledge findKnowledgeByKnowledge(String knowledge) {
		KnowledgeExample knowledgeExample = new KnowledgeExample();
		knowledgeExample.createCriteria().andKnowledgeEqualTo(knowledge);
		List<Knowledge> list = knowledgeMapper.selectByExample(knowledgeExample);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Knowledge> findKnowledgeBySubjectId(int subjectId) {
		KnowledgeExample knowledgeExample = new KnowledgeExample();
		knowledgeExample.createCriteria().andSubjectIdEqualTo(subjectId);
		List<Knowledge> list = knowledgeMapper.selectByExample(knowledgeExample);
		return list;
	}

}
