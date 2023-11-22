package com.oneteam.dormease.user.parents;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;
@Data
public class ParentsDto {
    private @SQLInjectionSafe int no;                  // COMMENT '학부모 번호'
    private @SQLInjectionSafe String id;               // COMMENT '학부모 아이디'
    private @SQLInjectionSafe String auth_no;          // COMMENT '인증번호'
    private @SQLInjectionSafe String name;             // COMMENT '학부모 이름'
    private @SQLInjectionSafe String password;         // COMMENT '학부모 비밀번호'
    private @SQLInjectionSafe int student_no;          // COMMENT '학생 번호'
    private @SQLInjectionSafe int relation;            // COMMENT '학부모 관계'
    private @SQLInjectionSafe int fail_count;          // COMMENT '로그인 실패 횟수'
    private @SQLInjectionSafe String phone;            // COMMENT '학부모 전화 번호'
    private @SQLInjectionSafe String mail;             // COMMENT '학부모 메일'
    private @SQLInjectionSafe String zip_code;         // COMMENT '학부모 집 우편번호'
    private @SQLInjectionSafe String school_no;        // COMMENT '학생 학교 번호'
    private @SQLInjectionSafe String school_name;      // COMMENT '학생 학교 번호'
    private @SQLInjectionSafe int student_grade;        // COMMENT '학생 학년'
    private @SQLInjectionSafe int student_class;        // COMMENT '학생 반'
    private @SQLInjectionSafe String address;          // COMMENT '학부모 주소'
    private @SQLInjectionSafe String address_detail1;  // COMMENT '학부모 상세 주소1'
    private @SQLInjectionSafe String address_detail2;  // COMMENT '학부모 상세 주소2'
    private @SQLInjectionSafe String reg_date;         // COMMENT '등록 일자'
    private @SQLInjectionSafe String mod_date;         // COMMENT '수정 일자'
}