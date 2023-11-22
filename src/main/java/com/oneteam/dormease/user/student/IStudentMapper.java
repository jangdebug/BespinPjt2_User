package com.oneteam.dormease.user.student;


import com.oneteam.dormease.user.student.leavePass.LeavePassDto;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentMapper {

    int insertNewStudent(StudentDto studentDto);
    StudentDto selectStudentByID(String id);
    int deleteStudentByNo(int no);
    int insertNewLeaveOut(LeavePassDto leavePassDto);

    void updateFailCount(StudentDto studentDto);
    int deleteLeavePassByNo(int no);

    int updateStudent(StudentDto studentDto);
}
