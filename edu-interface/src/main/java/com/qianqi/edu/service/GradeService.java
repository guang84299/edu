package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Grade;

public interface GradeService {

	void addGrade(Grade grade);
	void deleteGrade(Integer id);
	void updateGrade(Grade grade);
	Grade findGrade(Integer id);
	Grade findGradeByName(String name);
	List<Grade> findGradeAll();
}
