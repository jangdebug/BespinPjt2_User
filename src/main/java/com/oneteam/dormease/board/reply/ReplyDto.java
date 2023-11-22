package com.oneteam.dormease.board.reply;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class ReplyDto {
    private @SQLInjectionSafe int no;                 //COMMENT '답글 번호'
    private @SQLInjectionSafe String school_no;       //COMMENT '답글 학교 번호'
    private @SQLInjectionSafe int student_no;         //COMMENT '답글 학생 번호'
    private @SQLInjectionSafe String student_name;    //COMMENT '답글 학생 이름'
    private @SQLInjectionSafe int board_no;           //COMMENT '답글 게시판 번호'
    private @SQLInjectionSafe String comment;         // COMMENT '답글 내용'
    private @SQLInjectionSafe int group;              //COMMENT '답글 그룹'
    private @SQLInjectionSafe int step;               //COMMENT '답글 스텝'
    private @SQLInjectionSafe int indent;             //COMMENT '답글 인덴트'
    private @SQLInjectionSafe String reg_date;        // COMMENT '답글 등록 날짜'
    private @SQLInjectionSafe String mod_date;        //COMMENT '답글 수정 날짜'
}
