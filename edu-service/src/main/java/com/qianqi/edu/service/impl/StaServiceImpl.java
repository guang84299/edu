package com.qianqi.edu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianqi.edu.mapper.StaPaperMapper;
import com.qianqi.edu.mapper.StaQuestionMapper;
import com.qianqi.edu.pojo.StaPaper;
import com.qianqi.edu.pojo.StaPaperExample;
import com.qianqi.edu.pojo.StaPaperExample.Criteria;
import com.qianqi.edu.pojo.StaQuestion;
import com.qianqi.edu.pojo.StaQuestionExample;
import com.qianqi.edu.service.StaService;

@Service
public class StaServiceImpl implements StaService{

	@Autowired
	private StaPaperMapper staPaperMapper;
	@Autowired
	private StaQuestionMapper staQuestionMapper;
	
	@Override
	public void addStaPaper(StaPaper staPaper) {
		staPaperMapper.insertSelective(staPaper);
	}

	@Override
	public void deleteStaPaper(Long id) {
		staPaperMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateStaPaper(StaPaper staPaper) {
		staPaperMapper.updateByPrimaryKey(staPaper);
	}

	@Override
	public StaPaper findStaPaper(Long id) {
		return staPaperMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<StaPaper> findStaPaperByPaperId(long paperId)
	{
		StaPaperExample example = new StaPaperExample();
		example.createCriteria().andPaperIdEqualTo(paperId);
		return staPaperMapper.selectByExample(example);
	}

	@Override
	public List<StaPaper> findStaPapers(Integer schoolId, Integer gradeId, Long tclassId, Integer subjectId,
			Integer difficult, long fromTime, long toTime) {
		StaPaperExample example = new StaPaperExample();
		Criteria c = example.createCriteria().andCreatedBetween(new Date(fromTime), new Date(toTime));
		if(schoolId != null)
			c = c.andSchoolIdEqualTo(schoolId);
		if(gradeId != null)
			c = c.andGradeIdEqualTo(gradeId);
		if(tclassId != null)
			c = c.andTclassIdEqualTo(tclassId);
		if(subjectId != null)
			c = c.andSubjectIdEqualTo(subjectId);
		if(difficult != null)
			c = c.andDifficultEqualTo(difficult);
		
		return staPaperMapper.selectByExample(example);
	}
	
	@Override
	public List<StaPaper> findStaPaper(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Integer inobjective,Integer checkState,long fromTime,long toTime)
	{
		StaPaperExample example = new StaPaperExample();
		Criteria c = example.createCriteria().andCreatedBetween(new Date(fromTime), new Date(toTime));
		if(schoolId != null)
			c = c.andSchoolIdEqualTo(schoolId);
		if(gradeId != null)
			c = c.andGradeIdEqualTo(gradeId);
		if(tclassId != null)
			c = c.andTclassIdEqualTo(tclassId);
		if(subjectId != null)
			c = c.andSubjectIdEqualTo(subjectId);
		if(difficult != null)
			c = c.andDifficultEqualTo(difficult);
		if(inobjective != null)
			c = c.andInobjectiveEqualTo(inobjective);
		if(checkState != null)
			c = c.andCheckStateEqualTo(checkState);
		
		return staPaperMapper.selectByExample(example);
	}
	
	@Override
	public long countStaPapers(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Integer inobjective,Integer checkState,long fromTime,long toTime)
	{
		StaPaperExample example = new StaPaperExample();
		Criteria c = example.createCriteria().andCreatedBetween(new Date(fromTime), new Date(toTime));
		if(schoolId != null)
			c = c.andSchoolIdEqualTo(schoolId);
		if(gradeId != null)
			c = c.andGradeIdEqualTo(gradeId);
		if(tclassId != null)
			c = c.andTclassIdEqualTo(tclassId);
		if(subjectId != null)
			c = c.andSubjectIdEqualTo(subjectId);
		if(difficult != null)
			c = c.andDifficultEqualTo(difficult);
		if(inobjective != null)
			c = c.andInobjectiveEqualTo(inobjective);
		if(checkState != null)
			c = c.andCheckStateEqualTo(checkState);
		
		return staPaperMapper.countByExample(example);
	}

	
	//----------------------------------------------------------
	
	
	
	
	@Override
	public void addStaQuestion(StaQuestion staQuestion) {
		staQuestionMapper.insertSelective(staQuestion);
	}

	@Override
	public void deleteStaQuestion(Long id) {
		staQuestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateStaQuestion(StaQuestion staQuestion) {
		staQuestionMapper.updateByPrimaryKey(staQuestion);
	}

	@Override
	public StaQuestion findStaQuestion(Long id) {
		return staQuestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StaQuestion> findStaQuestions(Integer schoolId, Integer gradeId, Long tclassId, Integer subjectId,
			Integer difficult, Long knowledgeId, long fromTime, long toTime) {
		StaQuestionExample example = new StaQuestionExample();
		com.qianqi.edu.pojo.StaQuestionExample.Criteria c = example.createCriteria().andCreatedBetween(new Date(fromTime), new Date(toTime));
		if(schoolId != null)
			c = c.andSchoolIdEqualTo(schoolId);
		if(gradeId != null)
			c = c.andGradeIdEqualTo(gradeId);
		if(tclassId != null)
			c = c.andTclassIdEqualTo(tclassId);
		if(subjectId != null)
			c = c.andSubjectIdEqualTo(subjectId);
		if(difficult != null)
			c = c.andDifficultEqualTo(difficult);
		if(knowledgeId != null)
			c = c.andKnowledgeIdEqualTo(knowledgeId);
		
		return staQuestionMapper.selectByExample(example);
	}
	
	@Override
	public long countStaQuestions(Integer schoolId,Integer gradeId,Long tclassId,Integer subjectId,Integer difficult,Long knowledgeId,long fromTime,long toTime)
	{
		StaQuestionExample example = new StaQuestionExample();
		com.qianqi.edu.pojo.StaQuestionExample.Criteria c = example.createCriteria().andCreatedBetween(new Date(fromTime), new Date(toTime));
		if(schoolId != null)
			c = c.andSchoolIdEqualTo(schoolId);
		if(gradeId != null)
			c = c.andGradeIdEqualTo(gradeId);
		if(tclassId != null)
			c = c.andTclassIdEqualTo(tclassId);
		if(subjectId != null)
			c = c.andSubjectIdEqualTo(subjectId);
		if(difficult != null)
			c = c.andDifficultEqualTo(difficult);
		if(knowledgeId != null)
			c = c.andKnowledgeIdEqualTo(knowledgeId);
		
		return staQuestionMapper.countByExample(example);
	}

}
