package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.Question;

public class QuestionItem extends Question implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long paperItemId;
	private Long paperAnswerId;
    private Integer paperItemType;
    private Long paperId;
    private PaperAnswerItem paperAnswerItem;
    
	public QuestionItem() {}
	public QuestionItem(Question question) 
	{
		this.setId(question.getId());
		this.setType(question.getType());
		this.setAnswer(question.getAnswer());
		this.setScore(question.getScore());
		this.setTeacherId(question.getTeacherId());
		this.setSubjectId(question.getSubjectId());
		this.setGradeId(question.getGradeId());
		this.setDifficult(question.getDifficult());
		this.setKnowledgeId(question.getKnowledgeId());
		this.setNormalTime(question.getNormalTime());
		this.setCreated(question.getCreated());
		this.setUpdated(question.getUpdated());
		this.setContext(question.getContext());
		this.setChoiceA(question.getChoiceA());
		this.setChoiceB(question.getChoiceB());
		this.setChoiceC(question.getChoiceC());
		this.setChoiceD(question.getChoiceD());
	}
	public Long getPaperItemId() {
		return paperItemId;
	}
	public void setPaperItemId(Long paperItemId) {
		this.paperItemId = paperItemId;
	}
	
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	
	public Integer getPaperItemType() {
		return paperItemType;
	}
	public void setPaperItemType(Integer paperItemType) {
		this.paperItemType = paperItemType;
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
