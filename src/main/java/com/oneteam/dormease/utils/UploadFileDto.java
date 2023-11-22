package com.oneteam.dormease.utils;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class UploadFileDto {
    private @SQLInjectionSafe int board_no;              // COMMENT '업로드 된 게시판 보드 번호'
    private @SQLInjectionSafe String ori_file_name;      // COMMENT '첨부파일에 추가되는 파일명(원본)'
    private @SQLInjectionSafe String board_attach_file;  // COMMENT '첨부파일에 추가되는 파일명(변경)'
    private @SQLInjectionSafe String dir_name;           // COMMENT '첨부파일 경로'
}
