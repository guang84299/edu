package com.qianqi.edu.service;


import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface TeacherService {

	Teacher findTeacherById(Long id);
	
	void addTeacher(Teacher teacher);
	
	void updateTeacher(Teacher teacher);
	
	void deleteTeacher(Long id);
	
	EasyUIDataGridResult findTeacherList(int page,int rows);
}
