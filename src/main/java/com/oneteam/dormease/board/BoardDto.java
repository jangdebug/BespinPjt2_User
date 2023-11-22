package com.oneteam.dormease.board;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class BoardDto {
    private @SQLInjectionSafe int no;                    // COMMENT '글 번호'
    private @SQLInjectionSafe int school_no;             // COMMENT '학교 번호'
    private @SQLInjectionSafe int student_no;            // COMMENT '학생 번호'
    private @SQLInjectionSafe int class_no;              // COMMENT '반 번호'
    private @SQLInjectionSafe String student_name;       // COMMENT '학생 이름'
    private @SQLInjectionSafe int category_no;           // COMMENT '카테고리 번호'
    private @SQLInjectionSafe String title;              // COMMENT '글 제목'
    private @SQLInjectionSafe String content;            // COMMENT '글 내용'
    private @SQLInjectionSafe int like;                  // COMMENT '글 좋아요 수'
    private @SQLInjectionSafe int hit;                   // COMMENT '글 조회 수'
    private @SQLInjectionSafe String reg_date;           // COMMENT '글 등록 일자'
    private @SQLInjectionSafe String mod_date;           // COMMENT '글 수정 일자'

    private @SQLInjectionSafe int board_no;              // COMMENT '업로드 된 게시판 보드 번호'
    private @SQLInjectionSafe String ori_file_name;      // COMMENT '첨부파일에 추가되는 파일명(원본)'
    private @SQLInjectionSafe String board_attach_file;  // COMMENT '첨부파일에 추가되는 파일명(변경)'
    private @SQLInjectionSafe String dir_name;           // COMMENT '첨부파일 경로'
}