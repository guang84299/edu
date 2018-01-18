package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.StudentTclass;
import com.qianqi.edu.pojo.StudentTclassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentTclassMapper {
    int countByExample(StudentTclassExample example);

    int deleteByExample(StudentTclassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentTclass record);

    int insertSelective(StudentTclass record);

    List<StudentTclass> selectByExample(StudentTclassExample example);

    StudentTclass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StudentTclass record, @Param("example") StudentTclassExample example);

    int updateByExample(@Param("record") StudentTclass record, @Param("example") StudentTclassExample example);

    int updateByPrimaryKeySelective(StudentTclass record);

    int updateByPrimaryKey(StudentTclass record);
}