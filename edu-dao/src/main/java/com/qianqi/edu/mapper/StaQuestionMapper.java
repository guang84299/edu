package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.StaQuestion;
import com.qianqi.edu.pojo.StaQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaQuestionMapper {
    int countByExample(StaQuestionExample example);

    int deleteByExample(StaQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StaQuestion record);

    int insertSelective(StaQuestion record);

    List<StaQuestion> selectByExample(StaQuestionExample example);

    StaQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StaQuestion record, @Param("example") StaQuestionExample example);

    int updateByExample(@Param("record") StaQuestion record, @Param("example") StaQuestionExample example);

    int updateByPrimaryKeySelective(StaQuestion record);

    int updateByPrimaryKey(StaQuestion record);
}