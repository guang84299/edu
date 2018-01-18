package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Subject;

public interface SubjectService {
	void addSubject(Subject subject);
	void deleteSubject(Integer id);
	void updateSubject(Subject subject);
	Subject findSubject(Integer id);
	Subject findSubjectByName(String name);
	List<Subject> findSubjectAll();
}
