package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.Question;

public class SearchItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String context;
	private String knowledgePoint;
	private Long knowledgeId;
	
	public SearchItem() {}
	public SearchItem(Question question) 
	{
		this.setId(question.getId()+"");
		this.setContext(question.getContext());
		this.setKnowledgeId(question.getKnowledgeId());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getKnowledgePoint() {
		return knowledgePoint;
	}
	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}
	public Long getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	
	
	
	
}
