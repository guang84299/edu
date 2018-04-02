package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Notice;

public interface NoticeService {
	void addNotice(Notice notice);
	void deleteNotice(Long id);
	void updateNotice(Notice notice);
	Notice findNotice(Long id);
	List<Notice> findNoticeByType(Integer type);
	List<Notice> findNoticeAll();
}
