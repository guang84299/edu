package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.QuestionSingle;
import com.qianqi.edu.pojo.QuestionSingleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionSingleMapper {
    int countByExample(QuestionSingleExample example);

    int deleteByExample(QuestionSingleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuestionSingle record);

    int insertSelective(QuestionSingle record);

    List<QuestionSingle> selectByExampleWithBLOBs(QuestionSingleExample example);

    List<QuestionSingle> selectByExample(QuestionSingleExample example);

    QuestionSingle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuestionSingle record, @Param("example") QuestionSingleExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionSingle record, @Param("example") QuestionSingleExample example);

    int updateByExample(@Param("record") QuestionSingle record, @Param("example") QuestionSingleExample example);

    int updateByPrimaryKeySelective(QuestionSingle record);

    int updateByPrimaryKeyWithBLOBs(QuestionSingle record);

    int updateByPrimaryKey(QuestionSingle record);
}