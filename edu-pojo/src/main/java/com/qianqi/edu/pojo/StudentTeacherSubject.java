package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class StudentTeacherSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long studentId;

    private Long teacherSubjectId;

    private Date updated;

    private Date created;

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

    public Long getTeacherSubjectId() {
        return teacherSubjectId;
    }

    public void setTeacherSubjectId(Long teacherSubjectId) {
        this.teacherSubjectId = teacherSubjectId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
