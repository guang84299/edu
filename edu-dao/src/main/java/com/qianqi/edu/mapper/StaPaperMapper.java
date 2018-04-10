package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.StaPaper;
import com.qianqi.edu.pojo.StaPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaPaperMapper {
    int countByExample(StaPaperExample example);

    int deleteByExample(StaPaperExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StaPaper record);

    int insertSelective(StaPaper record);

    List<StaPaper> selectByExample(StaPaperExample example);

    StaPaper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StaPaper record, @Param("example") StaPaperExample example);

    int updateByExample(@Param("record") StaPaper record, @Param("example") StaPaperExample example);

    int updateByPrimaryKeySelective(StaPaper record);

    int updateByPrimaryKey(StaPaper record);
}