package com.qianqi.edu.pojo.common;

import java.io.Serializable;

public class StaSubjectData  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String subject;
	private long predictTime;
	private long actualTime;
	
	
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

	
	
}
