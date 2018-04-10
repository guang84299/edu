package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.Teacher;

public class TeacherData extends Teacher  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String school;
	private String grade;
	private String subject;
	private String tclass;
	private String stateStr;
	private String paperState;

	public TeacherData() {}
	public TeacherData(Teacher teacher)
	{
		this.setId(teacher.getId());
		this.setName(teacher.getName());
		this.setPassword(teacher.getPassword());
		this.setPhone(teacher.getPhone());
		this.setSchoolId(teacher.getSchoolId());
		this.setIdcard(teacher.getIdcard());
		this.setTidcard(teacher.getTidcard());
		this.setState(teacher.getState());
		this.setCreated(teacher.getCreated());
		this.setUpdated(teacher.getUpdated());
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	public String getPaperState() {
		return paperState;
	}
	public void setPaperState(String paperState) {
		this.paperState = paperState;
	}
	
	
	
}
