package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}