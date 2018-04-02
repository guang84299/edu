package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentTclass;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface StudentService {
	void addStudent(Student student);
	void deleteStudent(Long id);
	void updateStudent(Student student);
	Student findStudentById(Long id);
	Student findStudentByPhone(String phone);
	EasyUIDataGridResult findStudentList(int page,int rows);
	
	void addStudentTclass(StudentTclass studentTclass);
	void deleteStudentTclass(Long id);
	void updateStudentTclass(StudentTclass studentTclass);
	StudentTclass findStudentTclassById(Long id);
	List<StudentTclass> findStudentTclassByStudentId(Long studentId);
	List<StudentTclass> findStudentTclassByTclassId(Long tclassId);
	EasyUIDataGridResult findStudentTclassListByStudentId(Long studentId,int page,int rows);
	EasyUIDataGridResult findStudentTclassListByTclassId(Long tclassId,int page,int rows);
	EasyUIDataGridResult findStudentTclassListByTclassIds(List<Long> tclassId,int page,int rows);
}
