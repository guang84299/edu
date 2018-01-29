package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.QuestionCategoryMapper;
import com.qianqi.edu.mapper.QuestionMapper;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.QuestionCategoryExample;
import com.qianqi.edu.pojo.QuestionExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.SearchItem;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SearchService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionCategoryMapper  questionCategoryMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SearchService searchService;
	
	@Value("${QUESTION_LIST}")
	private String QUESTION_LIST; 
		
	@Value("${QUESTION_CATEGORY_LIST}")
	private String QUESTION_CATEGORY_LIST; 
	
	@Value("${QUESTION_ID}")
	private String QUESTION_ID; 
	@Value("${QUESTION_CATEGORY_ID}")
	private String QUESTION_CATEGORY_ID; 
	
	@Value("${QUESTION_NUM}")
	private String QUESTION_NUM; 
	@Value("${QUESTION_CATEGORY_NUM}")
	private String QUESTION_CATEGORY_NUM; 
	
	
	@Override
	public Question findQuestionById(Long id) {
		String data = jedisClient.hget(QUESTION_LIST,id+"");
		Question question = null;
		if(StringUtils.isEmpty(data))
		{
			question = questionMapper.selectByPrimaryKey(id);
			if(question != null)
			jedisClient.hset(QUESTION_LIST, id+"", JsonUtils.objectToJson(question));
		}
		else
		{
			question = JsonUtils.jsonToPojo(data, Question.class);
		}
		return question;
	}


	@Override
	public void addQuestion(Question question) {
		if(!jedisClient.exists(QUESTION_ID))
		{
			jedisClient.set(QUESTION_ID, QUESTION_NUM);
		}
		jedisClient.incr(QUESTION_ID);
		String num = jedisClient.get(QUESTION_ID);
		long id = Long.parseLong(num);
		question.setId(id);
		
		questionMapper.insertSelective(question);
		
		//添加到索引库
		searchService.addSearchItem(new SearchItem(question));
	}

	@Override
	public void updateQuestion(Question question) {
		questionMapper.updateByPrimaryKeySelective(question);
		jedisClient.hdel(QUESTION_LIST, question.getId()+"");
		
		searchService.addSearchItem(new SearchItem(question));
	}

	@Override
	public void deleteQuestion(Long id) {
		questionMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(QUESTION_LIST, id+"");
		
		searchService.deleteSearchItem(id+"");
	}

	@Override
	public EasyUIDataGridResult findQuestionList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		QuestionExample example = new QuestionExample();
		List<Question> list = questionMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Question> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	
	@Override
	public Integer addQuestionCategory(QuestionCategory category) {
		if(!jedisClient.exists(QUESTION_CATEGORY_ID))
		{
			jedisClient.set(QUESTION_CATEGORY_ID, QUESTION_CATEGORY_NUM);
		}
		jedisClient.incr(QUESTION_CATEGORY_ID);
		String num = jedisClient.get(QUESTION_CATEGORY_ID);
		int id = Integer.parseInt(num);
		category.setId(id);
		
		questionCategoryMapper.insertSelective(category);
		return id;
	}

	@Override
	public void deleteQuestionCategory(Integer id) {
		questionCategoryMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(QUESTION_CATEGORY_LIST, id+"");
	}

	@Override
	public void updateQuestionCategory(QuestionCategory category) {
		questionCategoryMapper.updateByPrimaryKeySelective(category);
		jedisClient.hdel(QUESTION_CATEGORY_LIST, category.getId()+"");
	}

	@Override
	public QuestionCategory findQuestionCategory(Integer id) {
		String data = jedisClient.hget(QUESTION_CATEGORY_LIST,id+"");
		QuestionCategory category = null;
		if(StringUtils.isEmpty(data))
		{
			category = questionCategoryMapper.selectByPrimaryKey(id);
			if(category != null)
			jedisClient.hset(QUESTION_CATEGORY_LIST, id+"", JsonUtils.objectToJson(category));
		}
		else
		{
			category = JsonUtils.jsonToPojo(data, QuestionCategory.class);
		}
		return category;
	}

	@Override
	public List<QuestionCategory> findQuestionCategoryByParentId(Integer parentId) {
		QuestionCategoryExample categoryExample = new QuestionCategoryExample();
		categoryExample.setOrderByClause("sort_order desc");
		categoryExample.createCriteria().andStatusEqualTo(1).andParentIdEqualTo(parentId);
		List<QuestionCategory> list = questionCategoryMapper.selectByExample(categoryExample);
		return list;
	}

	@Override
	public List<QuestionCategory> findQuestionCategoryByISParent() {
		QuestionCategoryExample categoryExample = new QuestionCategoryExample();
		categoryExample.setOrderByClause("sort_order desc");
		categoryExample.createCriteria().andStatusEqualTo(1).andIsParentEqualTo(true);
		List<QuestionCategory> list = questionCategoryMapper.selectByExample(categoryExample);
		return list;
	}

}
