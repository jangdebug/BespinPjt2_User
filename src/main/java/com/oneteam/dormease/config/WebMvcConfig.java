package com.oneteam.dormease.config;

import com.oneteam.dormease.product.ProductInterceptor;
import com.oneteam.dormease.user.member.UserInterceptor;
import com.oneteam.dormease.user.parents.ParentsInterceptor;
import com.oneteam.dormease.product.ProductOrderDto;
import com.oneteam.dormease.user.student.StudentInterceptor;
import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/dormEaseUploadImg/**")
                .addResourceLocations("file:///c://dormEase/upload/");

    }

    @Override
    public void addInterceptors (InterceptorRegistry registry) {

        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns(
                        "/user/student/**","/notice/**", "/image/upload", "/download", "/user/parents/**", "/product/**", "/board/**", "/reply/**"
                )
                .excludePathPatterns( "/user/parents/createAccountForm",
                        "/user/parents/createAccountConfirm",
                        "/user/parents/loginConfirm",
                        "/user/parents/searchStudents",
                        "/user/student/createAccountForm",
                        "/user/student/createAccountConfirm",
                        "/user/student/loginConfirm",
                        "/user/student/searchStudents",
                        "/user/student/logoutConfirm",
                        "/user/parents/logoutConfirm",
                        "/user/student/leaveOutForm",
                        "/user/student/leaveOutConfirm"
                );
        registry.addInterceptor(new ProductInterceptor())
                .addPathPatterns(
                        "/product/**", "/board/**", "/reply/**", "/user/student/leaveOutForm"
                );
    }

}
