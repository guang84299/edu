package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.StaPaper;
import com.qianqi.edu.pojo.StaQuestion;

public interface StaService {

	void addStaPaper(StaPaper staPaper);
	void deleteStaPaper(Long id);
	void updateStaPaper(StaPaper staPaper);
	StaPaper findStaPaper(Long id);
	List<StaPaper> findStaPaperByPaperId(long paperId);
	List<StaPaper> findStaPapers(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,long fromTime,long toTime);
	List<StaPaper> findStaPaper(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Integer inobjective,Integer checkState,long fromTime,long toTime);
	long countStaPapers(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Integer inobjective,Integer checkState,long fromTime,long toTime);
	
	
	void addStaQuestion(StaQuestion staQuestion);
	void deleteStaQuestion(Long id);
	void updateStaQuestion(StaQuestion staQuestion);
	StaQuestion findStaQuestion(Long id);
	List<StaQuestion> findStaQuestions(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Long knowledgeId,long fromTime,long toTime);
	long countStaQuestions(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Long knowledgeId,long fromTime,long toTime);
}
