package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface TclassService {
	void addTclass(Tclass tclass);
	void deleteTclass(Long id);
	void updateTclass(Tclass tclass);
	Tclass findTclass(Long id);
	List<Tclass> findTclassByIds(List<Long> ids);
	List<Tclass> findTclassByUserId(Long userId);
	List<Tclass> findTclassBySchoolId(int schoolId);
	List<Tclass> findTclassByGradeId(int gradeId);
	List<Tclass> findTclassBySchoolIdAndGradeId(int schoolId,int gradeId);
	EasyUIDataGridResult findTclassList(int page,int rows);
	EasyUIDataGridResult findTclassList(Long userId,int page,int rows);
}
