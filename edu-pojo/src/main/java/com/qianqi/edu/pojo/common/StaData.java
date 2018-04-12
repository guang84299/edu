package com.qianqi.edu.pojo.common;

import java.io.Serializable;

public class StaData  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String subject;
	private long predictTime;
	private long actualTime;
	
	private String knowledge;
	private float pre;//百分比
	
	private String school;
	
	private String tclass;
	
	private long checkTime;
	
	private float star;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(long predictTime) {
		this.predictTime = predictTime;
	}
	public long getActualTime() {
		return actualTime;
	}
	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public float getPre() {
		return pre;
	}
	public void setPre(float pre) {
		this.pre = pre;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	public long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(long checkTime) {
		this.checkTime = checkTime;
	}
	public float getStar() {
		return star;
	}
	public void setStar(float star) {
		this.star = star;
	}

	
	
}
