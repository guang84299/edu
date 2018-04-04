package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class PaperAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long studentId;

    private Long paperId;

    private Integer state;

    private Integer checkState;

    private Integer submitState;

    private Integer sEvaluate;

    private String sComment;

    private String tComment;

    private Date created;

    private Date checkTime;

    private Date submitTime;

    private String zhiAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public Integer getSubmitState() {
        return submitState;
    }

    public void setSubmitState(Integer submitState) {
        this.submitState = submitState;
    }

    public Integer getsEvaluate() {
        return sEvaluate;
    }

    public void setsEvaluate(Integer sEvaluate) {
        this.sEvaluate = sEvaluate;
    }

    public String getsComment() {
        return sComment;
    }

    public void setsComment(String sComment) {
        this.sComment = sComment == null ? null : sComment.trim();
    }

    public String gettComment() {
        return tComment;
    }

    public void settComment(String tComment) {
        this.tComment = tComment == null ? null : tComment.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getZhiAnswer() {
        return zhiAnswer;
    }

    public void setZhiAnswer(String zhiAnswer) {
        this.zhiAnswer = zhiAnswer == null ? null : zhiAnswer.trim();
    }
}
