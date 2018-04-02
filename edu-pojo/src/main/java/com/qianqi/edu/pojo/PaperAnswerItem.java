package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class PaperAnswerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long paperItemId;

    private Long paperAnswerId;

    private String answer;

    private Long answerTime;

    private Integer answerResult;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaperItemId() {
        return paperItemId;
    }

    public void setPaperItemId(Long paperItemId) {
        this.paperItemId = paperItemId;
    }

    public Long getPaperAnswerId() {
        return paperAnswerId;
    }

    public void setPaperAnswerId(Long paperAnswerId) {
        this.paperAnswerId = paperAnswerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(Integer answerResult) {
        this.answerResult = answerResult;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
