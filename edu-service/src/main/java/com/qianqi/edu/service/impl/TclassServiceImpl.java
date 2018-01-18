package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.TclassMapper;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.TclassExample;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.TclassService;

@Service
public class TclassServiceImpl implements TclassService{

	@Autowired
	private TclassMapper tclassMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${TCLASS_LIST}")
	private String TCLASS_LIST; 
	
	@Value("${TCLASS_ID}")
	private String TCLASS_ID; 
	
	@Value("${TCLASS_NUM}")
	private String TCLASS_NUM; 
	
	@Override
	public void addTclass(Tclass tclass) {
		if(!jedisClient.exists(TCLASS_ID))
		{
			jedisClient.set(TCLASS_ID, TCLASS_NUM);
		}
		jedisClient.incr(TCLASS_ID);
		String num = jedisClient.get(TCLASS_ID);
		long id = Long.parseLong(num);
		tclass.setId(id);
		tclassMapper.insertSelective(tclass);
	}

	@Override
	public void deleteTclass(Long id) {
		tclassMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(TCLASS_LIST, id+"");
	}

	@Override
	public void updateTclass(Tclass tclass) {
		tclassMapper.updateByPrimaryKeySelective(tclass);
		jedisClient.hdel(TCLASS_LIST, tclass.getId()+"");
	}

	@Override
	public Tclass findTclass(Long id) {
		String data = jedisClient.hget(TCLASS_LIST,id+"");
		Tclass tclass = null;
		if(StringUtils.isEmpty(data))
		{
			tclass = tclassMapper.selectByPrimaryKey(id);
			if(tclass != null)
				jedisClient.hset(TCLASS_LIST, id+"", JsonUtils.objectToJson(tclass));
		}
		else
		{
			tclass = JsonUtils.jsonToPojo(data, Tclass.class);
		}
		return tclass;
	}

	@Override
	public List<Tclass> findTclassByTeacherId(Long teacherId) {
		TclassExample tclassExample = new TclassExample();
		tclassExample.createCriteria().andTeacherIdEqualTo(teacherId);
		return tclassMapper.selectByExample(tclassExample);
	}

	@Override
	public EasyUIDataGridResult findTclassList(int page, int rows) {
		return findTclassList(null,page,rows);
	}

	@Override
	public EasyUIDataGridResult findTclassList(Long teacherId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TclassExample example = new TclassExample();
		if(teacherId != null)
			example.createCriteria().andTeacherIdEqualTo(teacherId);
		List<Tclass> list = tclassMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<Tclass> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

}
