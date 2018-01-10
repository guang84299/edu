package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class QuestionJudge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Boolean answer;

    private Integer score;

    private Long teacherId;

    private Integer difficult;

    private String knowledgePoint;

    private Integer normalTime;

    private Date created;

    private Date updated;

    private String context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint == null ? null : knowledgePoint.trim();
    }

    public Integer getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(Integer normalTime) {
        this.normalTime = normalTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}
