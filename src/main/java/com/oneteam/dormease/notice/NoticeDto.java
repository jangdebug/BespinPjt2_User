package com.oneteam.dormease.notice;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class NoticeDto {
    private @SQLInjectionSafe int no;                     // COMMENT '공지사항 번호',
    private @SQLInjectionSafe String school_no;           // COMMENT '공지사항 게시 학교 번호'
    private @SQLInjectionSafe String admin_name;          // COMMENT '공지사항 게시 학교 관리자 이름'
    private @SQLInjectionSafe int admin_no;          // COMMENT '공지사항 게시 학교 관리자 이름'
    private @SQLInjectionSafe String title;               // COMMENT '공지사항 제목'
    private @SQLInjectionSafe String content;             // COMMENT '공지사항 내용'
    private @SQLInjectionSafe int hit;                    // COMMENT '공지사항 조회 수'
    private @SQLInjectionSafe int fix;                    // COMMENT '공지사항 상단 게시 여부'
    private @SQLInjectionSafe int open;                   // COMMENT '공지사항 게시 여부'
    private @SQLInjectionSafe String start_date;          // COMMENT '공지사항 시작 날짜'
    private @SQLInjectionSafe String end_date;            // COMMENT '공지사항 종료 날짜'
    private @SQLInjectionSafe String reg_date;            // COMMENT '공지사항 등록 날짜'
    private @SQLInjectionSafe String mod_date;            // COMMENT '공지사항 수정 날짜'

    private @SQLInjectionSafe int notice_no;              // COMMENT '업로드 된 게시판 보드 번호'
    private @SQLInjectionSafe String ori_file_name;      // COMMENT '첨부파일에 추가되는 파일명(원본)'
    private @SQLInjectionSafe String notice_attach_file;  // COMMENT '첨부파일에 추가되는 파일명(변경)'
    private @SQLInjectionSafe String dir_name;           // COMMENT '첨부파일 경로'     // COMMENT '첨부파일에 추가되는 파일'
}
