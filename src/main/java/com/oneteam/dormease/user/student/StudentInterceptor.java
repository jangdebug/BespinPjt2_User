package com.oneteam.dormease.user.student;

import com.oneteam.dormease.user.parents.ParentsDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");

        if(loginedStudentDto != null ) {

            String message = "alert('권한이 없습니다!');";

            // 응답에 JavaScript 코드를 출력하고 문자 인코딩 설정
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>" + message + "</script>");
            out.flush();

            // JavaScript가 실행된 이후에 일정 시간(0.1초 후)에 리디렉션을 수행
            out.println("<script>setTimeout(function() { location.href = '" + request.getContextPath() + "/'; }, 100);</script>");
            return true;
        } else {
            return false;
        }
    }
}
