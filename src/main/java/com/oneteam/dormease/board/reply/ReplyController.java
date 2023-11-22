package com.oneteam.dormease.board.reply;

import com.oneteam.dormease.user.student.StudentDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/reply")
@Tag(name = "Reply", description = "게시글 댓글 API")
public class ReplyController {
    private final ReplyService replyService;
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/registReply")
    @ResponseBody
    public Object registReply(@RequestBody ReplyDto replyDto,HttpSession session) {
        log.info("registReply()");
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        replyDto.setSchool_no(loginedStudentDto.getSchool_no());
        replyDto.setStudent_no(loginedStudentDto.getNo());

        return replyService.registReply(replyDto);
    }
}
