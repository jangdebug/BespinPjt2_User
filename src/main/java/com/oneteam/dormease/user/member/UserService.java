package com.oneteam.dormease.user.member;

import com.oneteam.dormease.user.member.sms.SmsDTO;
import com.oneteam.dormease.user.parents.ParentsDto;
import com.oneteam.dormease.user.school.SchoolDto;
import com.oneteam.dormease.user.student.StudentDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class UserService {
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> checkIdDuplication(String id) {
        log.info("checkIdDuplication()");
        boolean isDuplicateID = userMapper.selectDuplicateByID(id);
        Map<String, Object> map = new HashMap<>();
        map.put("isDuplicateID", isDuplicateID);

        return map;
    }

    public Map<String, Object> checkCurrentPassword(String currentPassword, String no, boolean isStudent) {
        log.info("checkCurrentPassword()");
        boolean check = false;
        if (isStudent) {
            StudentDto studentDto = userMapper.selectStudentByNo(no);
            if (passwordEncoder.matches(currentPassword, studentDto.getPassword())) {
                check = true;
            }
        } else {
            ParentsDto parentsDto = userMapper.selectParentByNo(no);
            if (passwordEncoder.matches(currentPassword, parentsDto.getPassword())) {
                check = true;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("check", check);

        return map;

    }

    public int updatePassword(String no, String newPassword, boolean isStudent) {
        log.info("updatePassword()");
        String password = passwordEncoder.encode(newPassword);
        int result = -1;
        if (isStudent) {
            result = userMapper.updateStudentPassword(no,"0", password);
        } else {
            result = userMapper.updateParentPassword(no, "0", password);
        }
        return result;
    }

    public Map<String, Object> matchAuthentication(SmsDTO smsDTO) {
        log.info("matchAuthentication()");
        String encodedAuthNo = "";
        String id = "";
        Map<String, Object> map = new HashMap<>();
        if (smsDTO.isStudent()) {
            StudentDto studentDto = userMapper.selectStudentBySmsDto(smsDTO);
            encodedAuthNo = studentDto.getAuth_no();
            id = studentDto.getId();
        } else {
            ParentsDto parentsDto = userMapper.selectParentBySmsDto(smsDTO);
            encodedAuthNo = parentsDto.getAuth_no();
            id = parentsDto.getId();
        }
        if (!passwordEncoder.matches(smsDTO.getContent(), encodedAuthNo)) {
            map.put("result", false);
        } else {
            map.put("result", true);
            map.put("id", id);
        }

        return map;
    }

    public Map<String, Object> invalidateAuthenticationNumber(SmsDTO smsDTO) {
        log.info("invalidateAuthenticationNumber()");

        boolean result = false;
        Map<String, Object> map = new HashMap<>();
        smsDTO.setContent(null);

        if (smsDTO.isStudent()) {
            result = userMapper.updateStudentAuthNoBySmsDto(smsDTO);
        } else {
            result = userMapper.updateParentAuthNoBySmsDto(smsDTO);
        }
        map.put("result", result);

        return map;
    }

    public Object searchSchoolName(String name) {
        log.info("searchSchoolName()");
        Map<String, Object> map = new HashMap<>();
        List<SchoolDto> schoolDtos =  userMapper.selectSchoolsByName(name);
        map.put("schoolDtos", schoolDtos);

        return map;

    }
}
