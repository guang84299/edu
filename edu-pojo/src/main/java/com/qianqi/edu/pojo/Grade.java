package com.qianqi.edu.pojo;

import java.io.Serializable;

import java.util.Date;

public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
