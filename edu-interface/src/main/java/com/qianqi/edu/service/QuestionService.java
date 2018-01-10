package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.QuestionMulti;
import com.qianqi.edu.pojo.QuestionSingle;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface QuestionService {
	QuestionJudge findQuestionJudgeById(Long id);
	QuestionSingle findQuestionSingleById(Long id);
	QuestionMulti findQuestionMultiById(Long id);
	
	void addQuestionJudge(QuestionJudge judge);
	void addQuestionSingle(QuestionSingle single);
	void addQuestionMulti(QuestionMulti multi);
	
	void updateQuestionJudge(QuestionJudge judge);
	void updateQuestionSingle(QuestionSingle single);
	void updateQuestionMulti(QuestionMulti multi);
	
	void deleteQuestionJudge(Long id);
	void deleteQuestionSingle(Long id);
	void deleteQuestionMulti(Long id);
	
	EasyUIDataGridResult findQuestionJudgeList(int page,int rows);
	EasyUIDataGridResult findQuestionSingleList(int page,int rows);
	EasyUIDataGridResult findQuestionMultiList(int page,int rows);
	
	//分类
	Integer addQuestionCategory(QuestionCategory category);
	void deleteQuestionCategory(Integer id);
	void updateQuestionCategory(QuestionCategory category);
	QuestionCategory findQuestionCategory(Integer id);
	List<QuestionCategory> findQuestionCategoryByParentId(Integer parentId);
	List<QuestionCategory> findQuestionCategoryByISParent();
}
