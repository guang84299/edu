package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperAnswerMapper {
    int countByExample(PaperAnswerExample example);

    int deleteByExample(PaperAnswerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperAnswer record);

    int insertSelective(PaperAnswer record);

    List<PaperAnswer> selectByExample(PaperAnswerExample example);

    PaperAnswer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PaperAnswer record, @Param("example") PaperAnswerExample example);

    int updateByExample(@Param("record") PaperAnswer record, @Param("example") PaperAnswerExample example);

    int updateByPrimaryKeySelective(PaperAnswer record);

    int updateByPrimaryKey(PaperAnswer record);
}