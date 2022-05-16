package com.cream.zhxy_springboot.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginForm {

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
