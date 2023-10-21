package com.yh.model;

import lombok.Data;


@Data
public class Member {
    private Integer id;
    private String mname;
    private String nickname;
    private String mphoneNum;
    private String email;
}