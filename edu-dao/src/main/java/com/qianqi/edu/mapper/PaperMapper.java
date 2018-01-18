package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperMapper {
    int countByExample(PaperExample example);

    int deleteByExample(PaperExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Paper record);

    int insertSelective(Paper record);

    List<Paper> selectByExample(PaperExample example);

    Paper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Paper record, @Param("example") PaperExample example);

    int updateByExample(@Param("record") Paper record, @Param("example") PaperExample example);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
}