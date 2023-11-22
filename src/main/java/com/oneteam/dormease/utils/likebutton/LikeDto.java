package com.oneteam.dormease.utils.likebutton;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class LikeDto {
    private @SQLInjectionSafe int no;
    private @SQLInjectionSafe int board_no;
    private @SQLInjectionSafe String user_id;
    private @SQLInjectionSafe int total_like;
    private @SQLInjectionSafe String reg_date;
    private @SQLInjectionSafe String mod_date;
}
