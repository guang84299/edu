package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class TeacherSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long taecherId;

    private Integer schoolId;

    private Integer gradeId;

    private Long tclassId;

    private Integer subjectId;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaecherId() {
        return taecherId;
    }

    public void setTaecherId(Long taecherId) {
        this.taecherId = taecherId;
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

    public Long getTclassId() {
        return tclassId;
    }

    public void setTclassId(Long tclassId) {
        this.tclassId = tclassId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
