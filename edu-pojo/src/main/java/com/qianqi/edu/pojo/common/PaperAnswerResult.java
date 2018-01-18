package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.PaperAnswer;

public class PaperAnswerResult extends PaperAnswer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String subject;
	private String tclass;
	private String stateStr;
	
	public PaperAnswerResult() {}
	public PaperAnswerResult(PaperAnswer answer) 
	{
		this.setId(answer.getId());
		this.setPaperId(answer.getPaperId());
		this.setState(answer.getState());
		this.setStudentId(answer.getStudentId());
		this.setCreated(answer.getCreated());
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
	
	
}
