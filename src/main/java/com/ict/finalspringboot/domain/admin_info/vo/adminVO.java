package com.ict.finalspringboot.domain.admin_info.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminVO {

    private int admin_idx;
    private String admin_id;
    private int admin_level_idx;
    private String admin_pw;
    private String admin_profile;
    private String admin_name;
    private String admin_nickname;
    private String admin_email;
    private String admin_phone;
    private String admin_out;

}
