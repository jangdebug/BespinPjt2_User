package com.oneteam.dormease.user.member;

import com.oneteam.dormease.user.member.sms.SmsDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/user/member")
@Tag(name = "User", description = "사용자 공통 API")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * 학교 이름 검색
     */

    @GetMapping("/searchSchoolName")
    @ResponseBody
    public Object searchSchoolName(@RequestParam String name){
        log.info("searchSchoolName()");

       return userService.searchSchoolName(name);

    }
    /*
     * 로그인 폼
     */

    @GetMapping("/loginForm")
    public String loginForm(){
        log.info("loginForm()");

        return "user/loginForm";
    }

    /*
     * 아이디 중복 여부 체크
     */

    @GetMapping("/checkIdDuplication")
    @ResponseBody
    public Object checkIdDuplication(@RequestParam String id) {
        log.info("checkIdDuplication()");

        Map<String, Object> map = userService.checkIdDuplication(id);

        return map;

    }


    /*
     * 비밀번호 수정 폼
     */

    @GetMapping("/updatePasswordForm")
    public String updatePasswordForm(@RequestParam int no, @RequestParam boolean isStudent, Model model) {
        log.info("updatePasswordForm()");

        String nextPage = "user/member/updatePasswordForm";

        model.addAttribute("no", no);
        model.addAttribute("isStudent", isStudent);

        return nextPage;

    }

    /*
     * 비밀번호 수정 확인
     */
    @PostMapping("/checkCurrentPassword")
    @ResponseBody
    public Object checkCurrentPassword(@RequestBody Map<String, String> msgDto) {
        log.info("checkCurrentPassword()");

        boolean isStudent = Boolean.parseBoolean(msgDto.get("isStudent"));

        Map<String, Object> map = userService.checkCurrentPassword(msgDto.get("currentPassword"), msgDto.get("no"),isStudent);

        return map;

    }
    /*
     * 비밀번호 수정 확인
     */
    @PostMapping("/updatePasswordConfirm")
    public String updatePassword(@RequestParam(value = "no", required = false) String no, @RequestParam String newPassword, @RequestParam boolean isStudent) {
        log.info("updatePassword()");

        int result = userService.updatePassword(no, newPassword, isStudent);

        String nextPage = "";

        if(result > 0){
             nextPage = "redirect:/";
        } else {
            nextPage = "redirect:/user/member/loginForm";
        }

        return nextPage;

    }

    /*
     * 아이디 찾기 폼
     */
    @GetMapping("/findIDForm")
    public String findIDForm() {
        log.info("findIDForm()");
        String nextPage = "user/member/findIDForm";

        return nextPage;

    }
    /*
     * 비밀번호 찾기 폼
     */
    @GetMapping("/findPasswordForm")
    public String findPasswordForm() {
        log.info("findPasswordForm()");
        String nextPage = "user/member/findPasswordForm";

        return nextPage;

    }
    /*
     * 인증번호 확인
     */
    @PostMapping("/matchAuthentication")
    @ResponseBody
    public Object matchAuthentication(SmsDTO smsDTO, @RequestParam boolean isStudent) {
        log.info("matchAuthentication()");
        smsDTO.setStudent(isStudent);
        Map<String, Object> map = userService.matchAuthentication(smsDTO);

        return map;

    }
   /*
     * 인증번호 무효화
     */
    @PostMapping("/invalidateAuthenticationNumber")
    @ResponseBody
    public Object invalidateAuthenticationNumber(SmsDTO smsDTO, @RequestParam boolean isStudent) {
        log.info("invalidateAuthenticationNumber()");
        smsDTO.setStudent(isStudent);
        Map<String, Object> map = userService.invalidateAuthenticationNumber(smsDTO);

        return map;

    }
   
    /*
     * 회원가입 분기처리 페이지
     */
    @GetMapping("/beforeCreateAccount")
    public String beforeCreateAccount() {
        log.info("beforeCreateAccount()");
        String nextPage = "user/beforeCreateAccount";

        return nextPage;
    }


}
