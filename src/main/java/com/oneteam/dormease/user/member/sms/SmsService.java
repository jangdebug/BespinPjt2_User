package com.oneteam.dormease.user.member.sms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneteam.dormease.user.member.IUserMapper;
import com.oneteam.dormease.user.student.IStudentMapper;
import com.oneteam.dormease.utils.GenerateCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SmsService {

    private final IUserMapper userMapper;
    private final IStudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    private final String doesNotExistDataInDatabase = "1";

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;


    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        log.info("makeSignature()");

        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;

    }

    private HttpHeaders makeHeader() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        log.info("makeHeader()");
        Long time = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        return headers;

    }

    private SmsResponseDTO buildRequestDto(SmsDTO smsDTO, HttpHeaders headers) throws JsonProcessingException, URISyntaxException {
        List<SmsDTO> messages = new ArrayList<>();
        messages.add(smsDTO);

        SmsRequestDTO request = SmsRequestDTO.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content(smsDTO.getContent())
                .messages(messages)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"), httpBody, SmsResponseDTO.class);

        return response;
    }


    public Object sendAuthenticationMessage(SmsDTO smsDTO) throws JsonProcessingException, RestClientException, URISyntaxException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        log.info("sendAuthenticationMessage()");

        String authNo = GenerateCode.generateAuthNo();
        smsDTO.setContent(passwordEncoder.encode(authNo));

        if (smsDTO.isStudent()) {
            if (userMapper.selectStudentBySmsDto(smsDTO) != null) {
                userMapper.updateStudentAuthNoBySmsDto(smsDTO);
            } else {
                return doesNotExistDataInDatabase;
            }
        } else {
            if (userMapper.selectParentBySmsDto(smsDTO) != null) {
                userMapper.updateParentAuthNoBySmsDto(smsDTO);
            } else {
                return doesNotExistDataInDatabase;
            }

        }
        HttpHeaders headers = makeHeader();


        smsDTO.setContent("인증번호 [" + authNo + "]");
        SmsResponseDTO response = buildRequestDto(smsDTO, headers);

        return response;

    }

    public Object sendMessage(SmsDTO smsDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, JsonProcessingException {
        log.info("sendMessage()");
        HttpHeaders headers = makeHeader();
        if(!smsDTO.getContent().equals("null")) {
            smsDTO.setContent("아이디는 " + smsDTO.getContent() + "입니다.");
        } else {
            String password =  GenerateCode.generateRamdomPassword();
            if(smsDTO.isStudent()){
                userMapper.updateStudentPassword("0", smsDTO.getId(),passwordEncoder.encode(password));
            } else {
                userMapper.updateParentPassword("0", smsDTO.getId(),passwordEncoder.encode(password));
            }
            smsDTO.setContent("임시 비밀번호는 "+ password + "입니다.");
            userMapper.updateFailCount(smsDTO);
        }
        SmsResponseDTO response = buildRequestDto(smsDTO, headers);

        return response;
    }
}
