package com.oneteam.dormease.product;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.oneteam.dormease.user.student.StudentDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Data
public class ProductOrderDto {

    private @SQLInjectionSafe int no;                   //COMMENT '상품 주문 번호'
    private @SQLInjectionSafe String img;               //COMMENT '상품 이미지'
    private @SQLInjectionSafe String id;                //COMMENT '학생 ID'
    private @SQLInjectionSafe String name;             //COMMENT '상품 명'
    private @SQLInjectionSafe int count;                //COMMENT '상품 주문 수'
    private @SQLInjectionSafe int price;                //COMMENT '상품 개당 가격'
    private @SQLInjectionSafe int sum;                  //COMMENT '상품 별 총 합계'
    private @SQLInjectionSafe int allsum;               //COMMENT '총 합계'
    private @SQLInjectionSafe String reg_date;          //COMMENT '등록 날짜'
    private @SQLInjectionSafe String reg_date_trim;     //COMMENT '등록 날짜 공백제거'
    private @SQLInjectionSafe String mod_date;          //COMMENT '수정 날짜'

}
