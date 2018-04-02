package com.qianqi.edu.pojo;

import java.io.Serializable;

public class Knowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer subjectId;

    private String knowledge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge == null ? null : knowledge.trim();
    }
}
