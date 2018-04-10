package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class StaQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer schoolId;

    private Integer gradeId;

    private Integer subjectId;

    private Long tclassId;

    private Long teacherId;

    private Long studentId;

    private Long paperId;

    private Long questionId;

    private Integer difficult;

    private Long knowledgeId;

    private Long answerTime;

    private Integer answerResult;

    private Integer objective;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTclassId() {
        return tclassId;
    }

    public void setTclassId(Long tclassId) {
        this.tclassId = tclassId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
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

    public Integer getObjective() {
        return objective;
    }

    public void setObjective(Integer objective) {
        this.objective = objective;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
