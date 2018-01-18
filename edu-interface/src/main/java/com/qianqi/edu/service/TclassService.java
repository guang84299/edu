package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface TclassService {
	void addTclass(Tclass tclass);
	void deleteTclass(Long id);
	void updateTclass(Tclass tclass);
	Tclass findTclass(Long id);
	List<Tclass> findTclassByTeacherId(Long teacherId);
	EasyUIDataGridResult findTclassList(int page,int rows);
	EasyUIDataGridResult findTclassList(Long teacherId,int page,int rows);
}
