package com.oneteam.dormease.user.parents;

import com.oneteam.dormease.user.student.StudentDto;
import com.oneteam.dormease.utils.pagination.PageDefine;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/user/parents")
@Tag(name = "Parents", description = "학부모 API")
public class ParentsController {
    private final ParentsService parentsService;

    public ParentsController(ParentsService parentsService) {
        this.parentsService = parentsService;
    }

    /*
     *  회원 가입
     */

    @GetMapping("/createAccountForm")
    public String createAccountForm() {
        log.info("createAccountForm()");

        String nextPage = "user/parents/createAccountForm";

        return nextPage;

    }

    @GetMapping("/searchStudents")
    @ResponseBody
    public Object searchStudents(StudentDto studentDto) {
        log.info("searchStudents()");

        return parentsService.searchStudents(studentDto);

    }

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(ParentsDto parentsDto, Model model) {
        log.info("createAccountConfirm()");

        String nextPage = "user/parents/createAccountResult";

        int result = parentsService.createAccountConfirm(parentsDto);

        model.addAttribute("result", result);

        return nextPage;

    }

    /*
     * 로그인
     */
    @PostMapping("/loginConfirm")
    @ResponseBody
    public Object loginConfirm(ParentsDto parentsDto, HttpSession session) {
        log.info("loginConfirm()");

        Map<String, Object> map = parentsService.loginConfirm(parentsDto);
        ParentsDto loginedParentsDto = (ParentsDto) map.get("loginedParentsDto");
        if (loginedParentsDto != null) {
            session.setAttribute("loginedParentsDto", loginedParentsDto);
            session.setMaxInactiveInterval(30 * 60);
        }
        return map;

    }

    /*
     * 회원 수정
     */
    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session, Model model) {
        log.info("modifyAccountForm()");
        String nextPage = "user/parents/modifyAccountForm";
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        model.addAttribute("loginedParentsDto", loginedParentsDto);

        return nextPage;
    }

    /*
     * 회원 수정 확인
     */

    @PostMapping("/modifyAccountConfirm")
    public String modifyAccountConfirm(HttpSession session, Model model, ParentsDto parentsDto) {
        log.info("modifyAccountConfirm()");

        String nextPage = "user/parents/modifyAccountResult";

        ParentsDto loginedParentsDto = parentsService.modifyAccountConfirm(parentsDto);
        if(loginedParentsDto != null){
            session.setAttribute("loginedParentsDto", loginedParentsDto);
            session.setMaxInactiveInterval(30 * 60);
            model.addAttribute("result", 1);
        } else
            model.addAttribute("result", 0);

        return nextPage;
    }

    /*
     * 로그 아웃
     */
    @GetMapping("/logoutConfirm")
    public String logoutConfirm(HttpSession session) {
        log.info("logoutConfirm()");

        String nextPage = "redirect:/";

        session.removeAttribute("loginedParentsDto");

        return nextPage;
    }

    /*
     * 회원 탈퇴
     */
    @GetMapping("/deleteConfirm")
    @ResponseBody
    public Object deleteConfirm(HttpSession session,@RequestParam int no) {
        log.info("deleteConfirm()");
        int result = parentsService.deleteConfirm(no);
        if(result > 0){
            session.removeAttribute("loginedParentsDto");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);


        return map;
    }

    /*
     * 학생 외박 내역 리스트
     */
    @GetMapping("/leavePassList")
    public String leavePassList(Model model, HttpSession session,
                                @RequestParam(value = "pageNum", required = false, defaultValue = PageDefine.DEFAULT_PAGE_NUMBER) int pageNum,
                                @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_AMOUNT) int amount) {
        log.info("leavePassList()");

        String nextPage = "user/parents/leavePassList";
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        Map<String, Object> map = parentsService.leavePassList(loginedParentsDto.getStudent_no(), pageNum, amount);

        model.addAttribute("leavePassDtos", map.get("leavePassDtos"));
        model.addAttribute("pageMakerDto", map.get("pageMakerDto"));

        return nextPage;
    }

}
