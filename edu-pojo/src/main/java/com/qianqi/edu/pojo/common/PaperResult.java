package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.Paper;

public class PaperResult extends Paper implements Serializable{
	private static final long serialVersionUID = 1L;
	private String subject;
	private String tclass;
	private String stateStr;
	
	public PaperResult()
	{
		
	}
	
	public PaperResult(Paper paper)
	{
		this.setId(paper.getId());
		this.setName(paper.getName());
		this.setTeacherId(paper.getTeacherId());
		this.setSubjectId(paper.getSubjectId());
		this.setTclassId(paper.getTclassId());
		this.setState(paper.getState());
		this.setUpdated(paper.getUpdated());
		this.setCreated(paper.getCreated());
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
