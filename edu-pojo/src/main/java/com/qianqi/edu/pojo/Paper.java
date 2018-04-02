package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer type;

    private String name;

    private Long teacherId;

    private Integer subjectId;

    private Long tclassId;

    private Integer state;

    private Date updated;

    private Date created;

    private Long checkEvlTime;

    private String studentIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Long getTclassId() {
        return tclassId;
    }

    public void setTclassId(Long tclassId) {
        this.tclassId = tclassId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Long getCheckEvlTime() {
        return checkEvlTime;
    }

    public void setCheckEvlTime(Long checkEvlTime) {
        this.checkEvlTime = checkEvlTime;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds == null ? null : studentIds.trim();
    }
}
