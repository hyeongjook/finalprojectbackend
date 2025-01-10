package com.ict.finalspringboot.domain.ban.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanVO {

    private int user_stop_idx; 

    private int user_idx;    

    private Date stop_date; 

    private int stop_period;    

    private Date stop_end_date; 

    private String stop_reason;  

    private String stop_other;  


    private String user_id;    // 사용자 아이디
    private String user_name;  // 사용자 이름
    private String user_email; // 사용자 이메일
    private Date user_reg_date; // 사용자 가입일
}

