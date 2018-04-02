package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.School;

public interface SchoolService {
	void addSchool(School school);
	void deleteSchool(Integer id);
	void updateSchool(School school);
	School findSchool(Integer id);
	School findSchoolByName(String name);
	List<School> findSchoolAll();
}
