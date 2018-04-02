package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer type;

    private Long from;

    private Date created;

    private String notice;

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

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }
}
