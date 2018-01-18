package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.QuestionJudge;

public class QuestionResult extends QuestionJudge implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean ck;
	
	public QuestionResult() {}
	public QuestionResult(QuestionJudge question)
	{
		this.setId(question.getId());
		this.setAnswer(question.getAnswer());
		this.setScore(question.getScore());
		this.setTeacherId(question.getTeacherId());
		this.setSubjectId(question.getSubjectId());
		this.setGradeId(question.getGradeId());
		this.setDifficult(question.getDifficult());
		this.setKnowledgePoint(question.getKnowledgePoint());
		this.setNormalTime(question.getNormalTime());
		this.setCreated(question.getCreated());
		this.setUpdated(question.getUpdated());
		this.setContext(question.getContext());
	}
	
	public boolean getCk() {
		return ck;
	}
	public void setCk(boolean ck) {
		this.ck = ck;
	}
	
	
}
