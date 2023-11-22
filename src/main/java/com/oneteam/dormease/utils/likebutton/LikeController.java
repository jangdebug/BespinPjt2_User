package com.oneteam.dormease.utils.likebutton;

import com.oneteam.dormease.user.parents.ParentsDto;
import com.oneteam.dormease.user.student.StudentDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/like")
@Tag(name = "Like", description = "좋아요 API")
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/registLike")
    public Object registLike(@RequestParam LikeDto likeDto, HttpSession session) {
        log.info("registLike()");
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        if (loginedStudentDto != null) {
            likeDto.setUser_id(loginedStudentDto.getId());
        } else if (loginedParentsDto != null) {
            likeDto.setUser_id(loginedParentsDto.getId());
        }

//        return likeService.registLike(likeDto);
        return null;
    }

}
