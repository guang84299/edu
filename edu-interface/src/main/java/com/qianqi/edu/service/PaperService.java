package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface PaperService {

	Long addPaper(Paper paper);
	void deletePaper(Long id);
	void updatePaper(Paper paper);
	Paper findPaperById(Long id);
	List<Paper> findPaperByTeacherId(Long id);
	EasyUIDataGridResult findPaperList(int page,int rows);
	EasyUIDataGridResult findPaperList(Long teacherId,int page,int rows);
	EasyUIDataGridResult findPaperListByTclassIds(List<Long> tclassIds,int page,int rows);
	
	
	void addPaperAnswer(PaperAnswer answer);
	void deletePaperAnswer(Long id);
	void updatePaperAnswer(PaperAnswer answer);
	PaperAnswer findPaperAnswerById(Long id);
	PaperAnswer findPaperAnswerByStudentIdAndPaperId(Long studentId,Long paperId);
	List<PaperAnswer> findPaperAnswerByStudentId(Long studentId);
	List<PaperAnswer> findPaperAnswerByPaperId(Long paperId);
	List<PaperAnswer> findPaperAnswerByStudentIdAndState(Long studentId,int state);
	EasyUIDataGridResult findPaperAnswerByStudentId(Long studentId,int page,int rows);
	
	void addPaperAnswerItem(PaperAnswerItem item);
	void deletePaperAnswerItem(Long id);
	void updatePaperAnswerItem(PaperAnswerItem item);
	PaperAnswerItem findPaperAnswerItemById(Long id);
	PaperAnswerItem findPaperAnswerItem(Long paperItemId,Long paperAnswerId);
	
	
	void addPaperItem(PaperItem item);
	void deletePaperItem(Long id);
	void updatePaperItem(PaperItem item);
	PaperItem findPaperItemById(Long id);
	List<PaperItem> findPaperItemByPaperId(Long paperId);
	int findPaperItemNumByPaperId(Long paperId);
}
