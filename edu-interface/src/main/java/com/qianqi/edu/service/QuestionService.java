package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;

public interface QuestionService {
	Question findQuestionById(Long id);
	
	void addQuestion(Question question);
	
	void updateQuestion(Question question);
	
	void deleteQuestion(Long id);
	
	EasyUIDataGridResult findQuestionList(int page,int rows);
	EasyUIDataGridResult findQuestionListByInIds(List<Long> ids,int page,int rows);
	EasyUIDataGridResult findQuestionListByNotInIds(List<Long> ids,int page,int rows);
	EasyUIDataGridResult findQuestionList(List<Integer> types,List<Integer> subjectIds,List<Integer> gradeIds, List<Integer> difficults,int page,int rows);
	
	//分类
	Integer addQuestionCategory(QuestionCategory category);
	void deleteQuestionCategory(Integer id);
	void updateQuestionCategory(QuestionCategory category);
	QuestionCategory findQuestionCategory(Integer id);
	List<QuestionCategory> findQuestionCategoryByParentId(Integer parentId);
	List<QuestionCategory> findQuestionCategoryByISParent();
}
