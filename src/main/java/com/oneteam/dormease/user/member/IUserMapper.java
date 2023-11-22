package com.oneteam.dormease.user.member;

import com.oneteam.dormease.user.member.sms.SmsDTO;
import com.oneteam.dormease.user.parents.ParentsDto;
import com.oneteam.dormease.user.school.SchoolDto;
import com.oneteam.dormease.user.student.StudentDto;
import com.oneteam.dormease.user.student.leavePass.LeavePassDto;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper {
    boolean selectDuplicateByID(String id);
    List<SchoolDto> selectSchoolsByName(String name);

    StudentDto selectStudentByNo(String no);
    ParentsDto selectParentByNo(String no);
    int updateStudentPassword(String no, String id, String password);
    int updateParentPassword(String no, String id,String password);
    boolean updateStudentAuthNoBySmsDto(SmsDTO smsDTO);
    boolean updateParentAuthNoBySmsDto(SmsDTO smsDTO);
    StudentDto selectStudentBySmsDto(SmsDTO smsDTO);
    ParentsDto selectParentBySmsDto(SmsDTO smsDTO);
    void updateFailCount(SmsDTO smsDTO);

    List<LeavePassDto> selectLeavePass(PageMakerDto pageMakerDto);

    int selectLeavePassesCount(int no);
}
