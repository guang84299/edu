package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.PaperItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperItemMapper {
    int countByExample(PaperItemExample example);

    int deleteByExample(PaperItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperItem record);

    int insertSelective(PaperItem record);

    List<PaperItem> selectByExample(PaperItemExample example);

    PaperItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PaperItem record, @Param("example") PaperItemExample example);

    int updateByExample(@Param("record") PaperItem record, @Param("example") PaperItemExample example);

    int updateByPrimaryKeySelective(PaperItem record);

    int updateByPrimaryKey(PaperItem record);
}