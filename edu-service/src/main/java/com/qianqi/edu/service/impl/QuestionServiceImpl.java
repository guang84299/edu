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
import com.qianqi.edu.mapper.QuestionJudgeMapper;
import com.qianqi.edu.mapper.QuestionMultiMapper;
import com.qianqi.edu.mapper.QuestionSingleMapper;
import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.QuestionCategoryExample;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.QuestionJudgeExample;
import com.qianqi.edu.pojo.QuestionMulti;
import com.qianqi.edu.pojo.QuestionMultiExample;
import com.qianqi.edu.pojo.QuestionSingle;
import com.qianqi.edu.pojo.QuestionSingleExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionJudgeMapper questionJudgeMapper;
	@Autowired
	private QuestionMultiMapper questionMultiMapper;
	@Autowired
	private QuestionSingleMapper questionSingleMapper;
	@Autowired
	private QuestionCategoryMapper  questionCategoryMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${QUESTION_JUDGE_LIST}")
	private String QUESTION_JUDGE_LIST; 
	
	@Value("${QUESTION_SINGLE_LIST}")
	private String QUESTION_SINGLE_LIST; 
	
	@Value("${QUESTION_MULTI_LIST}")
	private String QUESTION_MULTI_LIST; 
	
	@Value("${QUESTION_CATEGORY_LIST}")
	private String QUESTION_CATEGORY_LIST; 
	
	@Value("${QUESTION_JUDGE_ID}")
	private String QUESTION_JUDGE_ID; 
	@Value("${QUESTION_SINGLE_ID}")
	private String QUESTION_SINGLE_ID; 
	@Value("${QUESTION_MULTI_ID}")
	private String QUESTION_MULTI_ID; 
	@Value("${QUESTION_CATEGORY_ID}")
	private String QUESTION_CATEGORY_ID; 
	
	@Value("${QUESTION_JUDGE_NUM}")
	private String QUESTION_JUDGE_NUM; 
	@Value("${QUESTION_SINGLE_NUM}")
	private String QUESTION_SINGLE_NUM; 
	@Value("${QUESTION_MULTI_NUM}")
	private String QUESTION_MULTI_NUM; 
	@Value("${QUESTION_CATEGORY_NUM}")
	private String QUESTION_CATEGORY_NUM; 
	
	
	@Override
	public QuestionJudge findQuestionJudgeById(Long id) {
		String data = jedisClient.hget(QUESTION_JUDGE_LIST,id+"");
		QuestionJudge judge = null;
		if(StringUtils.isEmpty(data))
		{
			judge = questionJudgeMapper.selectByPrimaryKey(id);
			jedisClient.hset(QUESTION_JUDGE_LIST, id+"", JsonUtils.objectToJson(judge));
		}
		else
		{
			judge = JsonUtils.jsonToPojo(data, QuestionJudge.class);
		}
		return judge;
	}

	@Override
	public QuestionSingle findQuestionSingleById(Long id) {
		String data = jedisClient.hget(QUESTION_SINGLE_LIST,id+"");
		QuestionSingle single = null;
		if(StringUtils.isEmpty(data))
		{
			single = questionSingleMapper.selectByPrimaryKey(id);
			jedisClient.hset(QUESTION_SINGLE_LIST, id+"", JsonUtils.objectToJson(single));
		}
		else
		{
			single = JsonUtils.jsonToPojo(data, QuestionSingle.class);
		}
		return single;
	}

	@Override
	public QuestionMulti findQuestionMultiById(Long id) {
		String data = jedisClient.hget(QUESTION_MULTI_LIST,id+"");
		QuestionMulti multi = null;
		if(StringUtils.isEmpty(data))
		{
			multi = questionMultiMapper.selectByPrimaryKey(id);
			jedisClient.hset(QUESTION_MULTI_LIST, id+"", JsonUtils.objectToJson(multi));
		}
		else
		{
			multi = JsonUtils.jsonToPojo(data, QuestionMulti.class);
		}
		return multi;
	}

	@Override
	public void addQuestionJudge(QuestionJudge judge) {
		if(!jedisClient.exists(QUESTION_JUDGE_ID))
		{
			jedisClient.set(QUESTION_JUDGE_ID, QUESTION_JUDGE_NUM);
		}
		jedisClient.incr(QUESTION_JUDGE_ID);
		String num = jedisClient.get(QUESTION_JUDGE_ID);
		long id = Long.parseLong(num);
		judge.setId(id);
		
		questionJudgeMapper.insertSelective(judge);
	}

	@Override
	public void addQuestionSingle(QuestionSingle single) {
		if(!jedisClient.exists(QUESTION_SINGLE_ID))
		{
			jedisClient.set(QUESTION_SINGLE_ID, QUESTION_SINGLE_NUM);
		}
		jedisClient.incr(QUESTION_SINGLE_ID);
		String num = jedisClient.get(QUESTION_SINGLE_ID);
		long id = Long.parseLong(num);
		single.setId(id);
		
		questionSingleMapper.insertSelective(single);
	}

	@Override
	public void addQuestionMulti(QuestionMulti multi) {
		if(!jedisClient.exists(QUESTION_MULTI_ID))
		{
			jedisClient.set(QUESTION_MULTI_ID, QUESTION_MULTI_NUM);
		}
		jedisClient.incr(QUESTION_MULTI_ID);
		String num = jedisClient.get(QUESTION_MULTI_ID);
		long id = Long.parseLong(num);
		multi.setId(id);
		
		questionMultiMapper.insertSelective(multi);
	}

	@Override
	public void updateQuestionJudge(QuestionJudge judge) {
		questionJudgeMapper.updateByPrimaryKeySelective(judge);
		jedisClient.hdel(QUESTION_JUDGE_LIST, judge.getId()+"");
	}

	@Override
	public void updateQuestionSingle(QuestionSingle single) {
		questionSingleMapper.updateByPrimaryKeySelective(single);
		jedisClient.hdel(QUESTION_SINGLE_LIST, single.getId()+"");
	}

	@Override
	public void updateQuestionMulti(QuestionMulti multi) {
		questionMultiMapper.updateByPrimaryKeySelective(multi);
		jedisClient.hdel(QUESTION_MULTI_LIST, multi.getId()+"");
	}

	@Override
	public void deleteQuestionJudge(Long id) {
		questionJudgeMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(QUESTION_JUDGE_LIST, id+"");
	}

	@Override
	public void deleteQuestionSingle(Long id) {
		questionSingleMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(QUESTION_SINGLE_LIST, id+"");
	}

	@Override
	public void deleteQuestionMulti(Long id) {
		questionMultiMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(QUESTION_MULTI_LIST, id+"");
	}

	@Override
	public EasyUIDataGridResult findQuestionJudgeList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		QuestionJudgeExample example = new QuestionJudgeExample();
		List<QuestionJudge> list = questionJudgeMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<QuestionJudge> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findQuestionSingleList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		QuestionSingleExample example = new QuestionSingleExample();
		List<QuestionSingle> list = questionSingleMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<QuestionSingle> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findQuestionMultiList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		QuestionMultiExample example = new QuestionMultiExample();
		List<QuestionMulti> list = questionMultiMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<QuestionMulti> pageInfo = new PageInfo<>(list);
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
