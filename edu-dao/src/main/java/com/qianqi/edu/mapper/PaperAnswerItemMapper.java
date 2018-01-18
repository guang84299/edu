package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperAnswerItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperAnswerItemMapper {
    int countByExample(PaperAnswerItemExample example);

    int deleteByExample(PaperAnswerItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperAnswerItem record);

    int insertSelective(PaperAnswerItem record);

    List<PaperAnswerItem> selectByExample(PaperAnswerItemExample example);

    PaperAnswerItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PaperAnswerItem record, @Param("example") PaperAnswerItemExample example);

    int updateByExample(@Param("record") PaperAnswerItem record, @Param("example") PaperAnswerItemExample example);

    int updateByPrimaryKeySelective(PaperAnswerItem record);

    int updateByPrimaryKey(PaperAnswerItem record);
}