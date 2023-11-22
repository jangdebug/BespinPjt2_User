package com.oneteam.dormease.user.student.leavePass;

import lombok.Data;

@Data
public class LeavePassDto {
    private int no;                     // COMMENT '외출증 번호'
    private int school_no;              // COMMENT '학교 번호'
    private String dormitory;           // COMMENT '기숙사 호실'
    private int student_no;             // COMMENT '학생 번호'
    private String student_id;          // COMMENT '학생 아이디'
    private String student_name;        // COMMENT '학생 이름'
    private int student_grade;          // COMMENT '학생 학년'
    private String content;             // COMMENT '외출 사유'
    private int admin_approval;         // COMMENT '관리자 승인'
    private String start_date;          // COMMENT '출발 날짜'
    private String end_date;            // COMMENT '예상 복귀 날짜'
    private String comeback_date;       // COMMENT '실제 복귀 날짜'
    private String reg_date;            // COMMENT '외출증 등록 날짜'
    private String mod_date;            // COMMENT '외출증 수정 날짜'
}
