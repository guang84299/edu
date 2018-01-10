package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.QuestionMulti;
import com.qianqi.edu.pojo.QuestionMultiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionMultiMapper {
    int countByExample(QuestionMultiExample example);

    int deleteByExample(QuestionMultiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuestionMulti record);

    int insertSelective(QuestionMulti record);

    List<QuestionMulti> selectByExampleWithBLOBs(QuestionMultiExample example);

    List<QuestionMulti> selectByExample(QuestionMultiExample example);

    QuestionMulti selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuestionMulti record, @Param("example") QuestionMultiExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionMulti record, @Param("example") QuestionMultiExample example);

    int updateByExample(@Param("record") QuestionMulti record, @Param("example") QuestionMultiExample example);

    int updateByPrimaryKeySelective(QuestionMulti record);

    int updateByPrimaryKeyWithBLOBs(QuestionMulti record);

    int updateByPrimaryKey(QuestionMulti record);
}