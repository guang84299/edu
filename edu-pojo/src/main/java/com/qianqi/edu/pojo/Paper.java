package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long teacherId;

    private Integer subjectId;

    private Long tclassId;

    private Integer state;

    private Date updated;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
