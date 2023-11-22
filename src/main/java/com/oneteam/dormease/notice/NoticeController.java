package com.oneteam.dormease.notice;

import com.oneteam.dormease.user.parents.ParentsDto;
import com.oneteam.dormease.user.student.StudentDto;
import com.oneteam.dormease.utils.pagination.PageDefine;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/notice")
@Tag(name = "Notice", description = "공지사항 API")
public class NoticeController {
    private final NoticeService noticeService;
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /*
     * 자유 게시판 게시글 리스트 페이지
     */
    @GetMapping("/noticeListForm")
    public String noticeListForm(HttpSession session, Model model,
                                 @RequestParam(value="keyWord", required = false) String keyWord,
                                 @RequestParam(value="search", required = false) String search,
                                 @RequestParam(value="pageNum", required = false, defaultValue = PageDefine.DEFAULT_PAGE_NUMBER) int pageNum,
                                 @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_AMOUNT) int amount) {
        log.info("noticeListForm()");
        String nextPage = "notice/noticeListForm";
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        String schoolNo = null;
        if (loginedStudentDto != null) {
            schoolNo = loginedStudentDto.getSchool_no();
        } else if (loginedParentsDto != null) {
            schoolNo = loginedParentsDto.getSchool_no();
        }
        Map<String, Object> resultMap = noticeService.getAllNoticeContent(schoolNo,keyWord, search, pageNum, amount);
        model.addAttribute("pageMakerDto", resultMap.get("pageMakerDto"));
        model.addAttribute("search", resultMap.get("search"));
        model.addAttribute("keyWord", resultMap.get("keyWord"));
        model.addAttribute("noticeDtos", resultMap.get("noticeDtos"));

        return nextPage;
    }

    /*
     * 게시글 디테일 페이지
     */
    @GetMapping("/detailNoticeForm")
    public String detailNoticeForm(@RequestParam("no") int no, Model model) {
        log.info("detailNoticeForm()");
        String nextPage = "notice/detailNoticeForm";
        Map<String, Object> noticeAndReplyMap = noticeService.getDetailNotice(no);
        model.addAttribute("noticeAndReplyMap", noticeAndReplyMap);

        return nextPage;
    }
}
