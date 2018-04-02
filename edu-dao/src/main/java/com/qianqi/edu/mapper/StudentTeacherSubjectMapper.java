package com.qianqi.edu.mapper;

import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.StudentTeacherSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentTeacherSubjectMapper {
    int countByExample(StudentTeacherSubjectExample example);

    int deleteByExample(StudentTeacherSubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentTeacherSubject record);

    int insertSelective(StudentTeacherSubject record);

    List<StudentTeacherSubject> selectByExample(StudentTeacherSubjectExample example);

    StudentTeacherSubject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StudentTeacherSubject record, @Param("example") StudentTeacherSubjectExample example);

    int updateByExample(@Param("record") StudentTeacherSubject record, @Param("example") StudentTeacherSubjectExample example);

    int updateByPrimaryKeySelective(StudentTeacherSubject record);

    int updateByPrimaryKey(StudentTeacherSubject record);
}