package com.qianqi.edu.pojo.common;

import java.io.Serializable;

import com.qianqi.edu.pojo.TeacherSubject;

public class TeacherSubjectData extends TeacherSubject  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String school;
	private String grade;
	private String tclass;
	private String subject;
	
	public TeacherSubjectData()
	{
		
	}
	public TeacherSubjectData(TeacherSubject teacherSubject)
	{
		this.setId(teacherSubject.getId());
		this.setSchoolId(teacherSubject.getSchoolId());
		this.setSubjectId(teacherSubject.getSubjectId());
		this.setTclassId(teacherSubject.getTclassId());
		this.setTeacherId(teacherSubject.getTeacherId());
		this.setGradeId(teacherSubject.getGradeId());
		this.setCreated(teacherSubject.getCreated());
		
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
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
