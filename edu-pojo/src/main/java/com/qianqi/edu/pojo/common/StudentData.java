package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.Student;

public class StudentData extends Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long studentTclassId;
	private String subject;
	private String tclass;
	
	public StudentData() {}
	public StudentData(Student student) {
		this.setId(student.getId());
		this.setName(student.getName());
		this.setPassword(student.getPassword());
		this.setPhone(student.getPhone());
		this.setState(student.getState());
		this.setUpdated(student.getUpdated());
		this.setCreated(student.getCreated());
	}
	public Long getStudentTclassId() {
		return studentTclassId;
	}
	public void setStudentTclassId(Long studentTclassId) {
		this.studentTclassId = studentTclassId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	
	
}
