package com.oneteam.dormease.user.parents;

import com.oneteam.dormease.user.student.StudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IParentsMapper {
    int insertNewParent(ParentsDto parentsDto);

    ParentsDto selectParent(ParentsDto parentsDto);

    int deleteParentByNo(int no);

    List<StudentDto> selectStudents(StudentDto studentDto);

    void updateFailCount(ParentsDto parentsDto);

    void updateParent(ParentsDto parentsDto);
}
