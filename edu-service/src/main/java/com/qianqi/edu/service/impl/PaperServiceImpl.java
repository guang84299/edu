package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.PaperAnswerItemMapper;
import com.qianqi.edu.mapper.PaperAnswerMapper;
import com.qianqi.edu.mapper.PaperItemMapper;
import com.qianqi.edu.mapper.PaperMapper;
import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerExample;
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperAnswerItemExample;
import com.qianqi.edu.pojo.PaperExample;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.PaperItemExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService{

	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private PaperItemMapper paperItemMapper;
	@Autowired
	private PaperAnswerMapper paperAnswerMapper;
	@Autowired
	private PaperAnswerItemMapper paperAnswerItemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${PAPER_LIST}")
	private String PAPER_LIST; 
	
	@Value("${PAPER_ID}")
	private String PAPER_ID; 
	
	@Value("${PAPER_NUM}")
	private String PAPER_NUM; 
	
	@Override
	public Long addPaper(Paper paper) {
		if(!jedisClient.exists(PAPER_ID))
		{
			jedisClient.set(PAPER_ID, PAPER_NUM);
		}
		jedisClient.incr(PAPER_ID);
		String num = jedisClient.get(PAPER_ID);
		long id = Long.parseLong(num);
		paper.setId(id);
		
		paperMapper.insertSelective(paper);
		return id;
	}

	@Override
	public void deletePaper(Long id) {
		paperMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(PAPER_LIST, id+"");
	}

	@Override
	public void updatePaper(Paper paper) {
		paperMapper.updateByPrimaryKeySelective(paper);
		jedisClient.hdel(PAPER_LIST, paper.getId()+"");
	}

	@Override
	public Paper findPaperById(Long id) {
		String data = jedisClient.hget(PAPER_LIST,id+"");
		Paper paper = null;
		if(StringUtils.isEmpty(data))
		{
			paper = paperMapper.selectByPrimaryKey(id);
			if(paper != null)
			jedisClient.hset(PAPER_LIST, id+"", JsonUtils.objectToJson(paper));
		}
		else
		{
			paper = JsonUtils.jsonToPojo(data, Paper.class);
		}
		return paper;
	}

	@Override
	public List<Paper> findPaperByTeacherId(Long id) {
		PaperExample paperExample = new PaperExample();
		paperExample.createCriteria().andTeacherIdEqualTo(id);
		return paperMapper.selectByExample(paperExample);
	}
	
	@Override
	public EasyUIDataGridResult findPaperList(int page, int rows) {
		return findPaperList(null,page,rows);
	}

	@Override
	public EasyUIDataGridResult findPaperList(Long teacherId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		PaperExample example = new PaperExample();
		if(teacherId != null)
			example.createCriteria().andTeacherIdEqualTo(teacherId);
		List<Paper> list = paperMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Paper> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult findPaperListByTclassIds(List<Long> tclassIds, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		PaperExample example = new PaperExample();
		example.createCriteria().andTclassIdIn(tclassIds)
		.andStateEqualTo(2);
		List<Paper> list = paperMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Paper> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	
	
	//---------------------------------
	
	@Override
	public void addPaperAnswer(PaperAnswer answer) {
		paperAnswerMapper.insertSelective(answer);
	}

	@Override
	public void deletePaperAnswer(Long id) {
		paperAnswerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePaperAnswer(PaperAnswer answer) {
		paperAnswerMapper.updateByPrimaryKeySelective(answer);
	}

	@Override
	public PaperAnswer findPaperAnswerById(Long id) {
		return paperAnswerMapper.selectByPrimaryKey(id);
	}

	@Override
	public PaperAnswer findPaperAnswerByStudentIdAndPaperId(Long studentId, Long paperId) {
		PaperAnswerExample paperAnswerExample = new PaperAnswerExample();
		paperAnswerExample.createCriteria().andStudentIdEqualTo(studentId)
		.andPaperIdEqualTo(paperId);
		List<PaperAnswer> list = paperAnswerMapper.selectByExample(paperAnswerExample);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}
	

	@Override
	public List<PaperAnswer> findPaperAnswerByStudentId(Long studentId) {
		PaperAnswerExample paperAnswerExample = new PaperAnswerExample();
		paperAnswerExample.createCriteria().andStudentIdEqualTo(studentId);
		return paperAnswerMapper.selectByExample(paperAnswerExample);
	}

	@Override
	public List<PaperAnswer> findPaperAnswerByPaperId(Long paperId) {
		PaperAnswerExample paperAnswerExample = new PaperAnswerExample();
		paperAnswerExample.createCriteria().andPaperIdEqualTo(paperId);
		return paperAnswerMapper.selectByExample(paperAnswerExample);
	}
	
	@Override
	public List<PaperAnswer> findPaperAnswerByStudentIdAndState(Long studentId,int state) {
		PaperAnswerExample paperAnswerExample = new PaperAnswerExample();
		paperAnswerExample.createCriteria().andStudentIdEqualTo(studentId)
		.andStateLessThan(state);
		return paperAnswerMapper.selectByExample(paperAnswerExample);
	}
	
	@Override
	public EasyUIDataGridResult findPaperAnswerByStudentId(Long studentId,int page,int rows) {		
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		PaperAnswerExample example = new PaperAnswerExample();
		example.createCriteria().andStudentIdEqualTo(studentId);
		List<PaperAnswer> list = paperAnswerMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<PaperAnswer> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	
	//--------------------------------
	
	@Override
	public void addPaperAnswerItem(PaperAnswerItem item) {
		paperAnswerItemMapper.insertSelective(item);
	}

	@Override
	public void deletePaperAnswerItem(Long id) {
		paperAnswerItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePaperAnswerItem(PaperAnswerItem item) {
		paperAnswerItemMapper.updateByPrimaryKey(item);
	}

	@Override
	public PaperAnswerItem findPaperAnswerItemById(Long id) {
		return paperAnswerItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public PaperAnswerItem findPaperAnswerItem(Long paperItemId,Long paperAnswerId) {
		PaperAnswerItemExample example = new PaperAnswerItemExample();
		example.createCriteria().andPaperAnswerIdEqualTo(paperAnswerId)
		.andPaperItemIdEqualTo(paperItemId);
		List<PaperAnswerItem> list = paperAnswerItemMapper.selectByExample(example);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	
	
	
	
	//----------------------------------
	
	
	@Override
	public void addPaperItem(PaperItem item) {
		paperItemMapper.insertSelective(item);
	}

	@Override
	public void deletePaperItem(Long id) {
		paperItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePaperItem(PaperItem item) {
		paperItemMapper.updateByPrimaryKey(item);
	}

	@Override
	public PaperItem findPaperItemById(Long id) {
		return paperItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PaperItem> findPaperItemByPaperId(Long paperId) {
		PaperItemExample example = new PaperItemExample();
		example.createCriteria().andPaperIdEqualTo(paperId);
		
		return paperItemMapper.selectByExample(example);
	}

	@Override
	public int findPaperItemNumByPaperId(Long paperId) {
		PaperItemExample example = new PaperItemExample();
		example.createCriteria().andPaperIdEqualTo(paperId);
		return paperItemMapper.countByExample(example);
	}

	
	
}
