package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.common.SearchItem;

public interface SearchService {
	
	void synchroSearchItem();
	void addSearchItem(SearchItem item);
	void deleteSearchItem(String id);
	List<SearchItem > searchQuestion(String keyword, int page,int rows);
	
}
