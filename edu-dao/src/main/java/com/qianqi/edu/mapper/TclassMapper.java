package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.TclassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TclassMapper {
    int countByExample(TclassExample example);

    int deleteByExample(TclassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tclass record);

    int insertSelective(Tclass record);

    List<Tclass> selectByExample(TclassExample example);

    Tclass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tclass record, @Param("example") TclassExample example);

    int updateByExample(@Param("record") Tclass record, @Param("example") TclassExample example);

    int updateByPrimaryKeySelective(Tclass record);

    int updateByPrimaryKey(Tclass record);
}