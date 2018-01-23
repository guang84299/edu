package com.qianqi.edu.laboratory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search/toimportall")
	public String toimportall()
	{
		return "search-item";
	}
	
	@RequestMapping("/search/importall")
	@ResponseBody
	public EduResult importall()
	{
		searchService.synchroSearchItem();
		return EduResult.ok(null, null);
	}
}
