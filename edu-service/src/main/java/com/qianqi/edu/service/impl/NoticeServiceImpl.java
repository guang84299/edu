package com.qianqi.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qianqi.edu.common.JsonUtils;
import com.qianqi.edu.mapper.NoticeMapper;
import com.qianqi.edu.pojo.Notice;
import com.qianqi.edu.pojo.NoticeExample;
import com.qianqi.edu.service.JedisClient;
import com.qianqi.edu.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${NOTICE_LIST}")
	private String NOTICE_LIST; 
	
	@Override
	public void addNotice(Notice notice) {
		noticeMapper.insertSelective(notice);
	}

	@Override
	public void deleteNotice(Long id) {
		noticeMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(NOTICE_LIST, id+"");
	}

	@Override
	public void updateNotice(Notice notice) {
		noticeMapper.updateByPrimaryKeySelective(notice);
		jedisClient.hdel(NOTICE_LIST, notice.getId()+"");
	}

	@Override
	public Notice findNotice(Long id) {
		String data = jedisClient.hget(NOTICE_LIST,id+"");
		Notice notice = null;
		if(StringUtils.isEmpty(data))
		{
			notice = noticeMapper.selectByPrimaryKey(id);
			if(notice != null)
			jedisClient.hset(NOTICE_LIST, id+"", JsonUtils.objectToJson(notice));
		}
		else
		{
			notice = JsonUtils.jsonToPojo(data, Notice.class);
		}
		return notice;
	}

	@Override
	public List<Notice> findNoticeByType(Integer type) {
		NoticeExample noticeExample = new NoticeExample();
		noticeExample.createCriteria().andTypeEqualTo(type);
		List<Notice> list = noticeMapper.selectByExample(noticeExample);
		
		return list;
	}

	@Override
	public List<Notice> findNoticeAll() {
		NoticeExample noticeExample = new NoticeExample();
		return noticeMapper.selectByExample(noticeExample);
	}

}
