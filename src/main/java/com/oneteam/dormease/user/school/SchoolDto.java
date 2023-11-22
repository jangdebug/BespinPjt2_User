package com.oneteam.dormease.user.school;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class SchoolDto {
    private @SQLInjectionSafe String school_code;     // COMMENT '학교 번호'
    private @SQLInjectionSafe String atpt_name;       // COMMENT '교육부'
    private @SQLInjectionSafe String zip_code;        // COMMENT '학교 우편 번호'
    private @SQLInjectionSafe String school_name;     // COMMENT '학교 이름'
    private @SQLInjectionSafe String address;         // COMMENT '학교 주소'
    private @SQLInjectionSafe String url;             // COMMENT '학교 URL 주소'
    private @SQLInjectionSafe String school_knd;      // COMMENT '학교 종류'
    private @SQLInjectionSafe String status;          // COMMENT '활성화'
}
