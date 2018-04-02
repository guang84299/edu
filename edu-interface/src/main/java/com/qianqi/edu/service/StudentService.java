package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface StudentService {
	void addStudent(Student student);
	void deleteStudent(Long id);
	void updateStudent(Student student);
	Student findStudentById(Long id);
	Student findStudentByPhone(String phone);
	EasyUIDataGridResult findStudentList(int page,int rows);
	
	void addStudentTeacherSubject(StudentTeacherSubject studentTeacherSubject);
	void deleteStudentTeacherSubject(Long id);
	void updateStudentTeacherSubject(StudentTeacherSubject studentTeacherSubject);
	StudentTeacherSubject findStudentTeacherSubjectById(Long id);
	List<StudentTeacherSubject> findStudentTeacherSubjectByStudentId(Long studentId);
	List<StudentTeacherSubject> findStudentTeacherSubjectByTeacherSubjectId(Long teacherSubjectId);
	EasyUIDataGridResult findStudentTeacherSubjectListByStudentId(Long studentId,int page,int rows);
	EasyUIDataGridResult findStudentTeacherSubjectListByTeacherSubjectId(Long teacherSubjectId,int page,int rows);
	EasyUIDataGridResult findStudentTeacherSubjectListByTeacherSubjectIds(List<Long> teacherSubjectIds,int page,int rows);
}
