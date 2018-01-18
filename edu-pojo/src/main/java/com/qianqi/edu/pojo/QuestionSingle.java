package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class QuestionSingle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String answer;

    private Integer score;

    private Long teacherId;

    private Integer subjectId;

    private Integer gradeId;

    private Integer difficult;

    private String knowledgePoint;

    private Integer normalTime;

    private String choiceA;

    private String choiceB;

    private String choiceC;

    private String choiceD;

    private String choiceE;

    private String choiceF;

    private Date created;

    private Date updated;

    private String context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA == null ? null : choiceA.trim();
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB == null ? null : choiceB.trim();
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC == null ? null : choiceC.trim();
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD == null ? null : choiceD.trim();
    }

    public String getChoiceE() {
        return choiceE;
    }

    public void setChoiceE(String choiceE) {
        this.choiceE = choiceE == null ? null : choiceE.trim();
    }

    public String getChoiceF() {
        return choiceF;
    }

    public void setChoiceF(String choiceF) {
        this.choiceF = choiceF == null ? null : choiceF.trim();
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
