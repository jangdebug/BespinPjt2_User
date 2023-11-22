package com.oneteam.dormease.user.member.sms;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDTO {
    private @SQLInjectionSafe int no;
    private @SQLInjectionSafe String id;
    private @SQLInjectionSafe String name;
    private @SQLInjectionSafe boolean isStudent;
    private @SQLInjectionSafe String to;
    private @SQLInjectionSafe String content;
}
