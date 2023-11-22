package com.oneteam.dormease.user.student;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class StudentDto {
    private @SQLInjectionSafe int no;                 // COMMENT '학생 번호'
    private @SQLInjectionSafe String id;              // COMMENT '학생 아이디'
    private @SQLInjectionSafe String auth_no;         // COMMENT '인증번호'
    private @SQLInjectionSafe String password;        // COMMENT '학생 비밀번호'
    private @SQLInjectionSafe String phone;           // COMMENT '학생 전화번호'
    private @SQLInjectionSafe String mail;            // COMMENT '학생 메일'
    private @SQLInjectionSafe int gender;             // COMMENT '학생 성별'
    private @SQLInjectionSafe String name;            // COMMENT '학생 이름'
    private @SQLInjectionSafe String zip_code;        // COMMENT '학생 주소 우편번호'
    private @SQLInjectionSafe String address;         // COMMENT '학생 주소'
    private @SQLInjectionSafe String address_detail1; // COMMENT '학생 상세 주소1'
    private @SQLInjectionSafe String address_detail2; // COMMENT '학생 상세 주소2'
    private @SQLInjectionSafe String parent;          // COMMENT '부모'
    private @SQLInjectionSafe String school_no;       // COMMENT '학교 번호'
    private @SQLInjectionSafe String school_name;     // COMMENT '학교 이름'
    private @SQLInjectionSafe String school_zip_code; // COMMENT '학교 우편번호'
    private @SQLInjectionSafe int grade;              // COMMENT '학생 학년'
    private @SQLInjectionSafe int class_no;           // COMMENT '학생 반'
    private @SQLInjectionSafe int eigen_no;           // COMMENT '학생 상 벌점'
    private @SQLInjectionSafe int dormitory;          // COMMENT '학생 기숙사 호실'
    private @SQLInjectionSafe int point;              // COMMENT '학생 기숙사 호실'
    private @SQLInjectionSafe int fail_count;         // COMMENT '학생 로그인 실패 횟수'
    private @SQLInjectionSafe String reg_date;        // COMMENT '학생 등록 일자'
    private @SQLInjectionSafe String mod_date;        // COMMENT '학생 수정 일자'
}
