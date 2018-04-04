package com.qianqi.edu.service;


import java.util.List;

import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface TeacherService {

	Teacher findTeacherById(Long id);
	
	void addTeacher(Teacher teacher);
	
	void updateTeacher(Teacher teacher);
	
	void deleteTeacher(Long id);
	
	EasyUIDataGridResult findTeacherList(int page,int rows);
	EasyUIDataGridResult findTeacherList(List<Integer> schools,List<Integer> gradeIds,List<Integer> subjectIds,int page,int rows);
	
	
	
	void addTeacherSubject(TeacherSubject teacherSubject);
	void deleteTeacherSubject(Long id);
	void updateTeacherSubject(TeacherSubject teacherSubject);
	TeacherSubject findTeacherSubject(Long id);
	List<TeacherSubject> findTeacherSubjectByTeacherId(Long teacherId);
	EasyUIDataGridResult findTeacherSubjectList(int page,int rows);
	EasyUIDataGridResult findTeacherSubjectList(Long teacherId,int page,int rows);
}
