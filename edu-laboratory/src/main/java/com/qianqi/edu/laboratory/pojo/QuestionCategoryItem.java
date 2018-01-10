package com.qianqi.edu.laboratory.pojo;

import java.io.Serializable;

public class QuestionCategoryItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String text;
	private String state;
	
	public QuestionCategoryItem() {};
	public QuestionCategoryItem(int id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
