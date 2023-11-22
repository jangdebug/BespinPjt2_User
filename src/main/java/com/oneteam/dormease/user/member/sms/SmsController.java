package com.oneteam.dormease.user.member.sms;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@Controller
@RequestMapping("/sms")
@Tag(name = "Sms", description = "sms 전송 API")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService){
        this.smsService = smsService;
    }

    @PostMapping("/sendAuthenticationMessage")
    @ResponseBody
    public Object sendAuthenticationMessage(SmsDTO smsDTO, @RequestParam boolean isStudent) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        log.info("sendAuthenticationMessage()");
        smsDTO.setStudent(isStudent);

        return smsService.sendAuthenticationMessage(smsDTO);

    }
    @PostMapping("/sendMessage")
    @ResponseBody
    public Object sendMessage(SmsDTO smsDTO, @RequestParam(required = false) boolean isStudent) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        log.info("sendMessage()");
        smsDTO.setStudent(isStudent);
        return smsService.sendMessage(smsDTO);

    }



}
