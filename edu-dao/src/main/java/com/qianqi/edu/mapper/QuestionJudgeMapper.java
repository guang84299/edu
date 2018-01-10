package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.QuestionJudgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionJudgeMapper {
    int countByExample(QuestionJudgeExample example);

    int deleteByExample(QuestionJudgeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuestionJudge record);

    int insertSelective(QuestionJudge record);

    List<QuestionJudge> selectByExampleWithBLOBs(QuestionJudgeExample example);

    List<QuestionJudge> selectByExample(QuestionJudgeExample example);

    QuestionJudge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuestionJudge record, @Param("example") QuestionJudgeExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionJudge record, @Param("example") QuestionJudgeExample example);

    int updateByExample(@Param("record") QuestionJudge record, @Param("example") QuestionJudgeExample example);

    int updateByPrimaryKeySelective(QuestionJudge record);

    int updateByPrimaryKeyWithBLOBs(QuestionJudge record);

    int updateByPrimaryKey(QuestionJudge record);
}