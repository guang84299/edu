package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.TeacherSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherSubjectMapper {
    int countByExample(TeacherSubjectExample example);

    int deleteByExample(TeacherSubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TeacherSubject record);

    int insertSelective(TeacherSubject record);

    List<TeacherSubject> selectByExample(TeacherSubjectExample example);

    TeacherSubject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeacherSubject record, @Param("example") TeacherSubjectExample example);

    int updateByExample(@Param("record") TeacherSubject record, @Param("example") TeacherSubjectExample example);

    int updateByPrimaryKeySelective(TeacherSubject record);

    int updateByPrimaryKey(TeacherSubject record);
}