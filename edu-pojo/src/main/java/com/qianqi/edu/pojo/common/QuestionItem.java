package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.QuestionJudge;

public class QuestionItem extends QuestionJudge implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long paperItemId;
	private Long paperAnswerId;
    private Integer type;
    private Long paperId;
    private Integer questionType;
    private PaperAnswerItem paperAnswerItem;
    
	public QuestionItem() {}
	public QuestionItem(QuestionJudge question) 
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
	public Long getPaperItemId() {
		return paperItemId;
	}
	public void setPaperItemId(Long paperItemId) {
		this.paperItemId = paperItemId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	public Long getPaperAnswerId() {
		return paperAnswerId;
	}
	public void setPaperAnswerId(Long paperAnswerId) {
		this.paperAnswerId = paperAnswerId;
	}
	public PaperAnswerItem getPaperAnswerItem() {
		return paperAnswerItem;
	}
	public void setPaperAnswerItem(PaperAnswerItem paperAnswerItem) {
		this.paperAnswerItem = paperAnswerItem;
	}

	
	
}
