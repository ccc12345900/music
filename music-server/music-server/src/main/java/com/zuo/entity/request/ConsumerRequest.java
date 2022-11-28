package com.zuo.entity.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ConsumerRequest {
    private Integer id;

    private String username;

    private String password;

    private String oldPassword;

    private Integer sex;

    private String phoneNum;

    private String email;

    private LocalDateTime birth;

    private String introduction;

    private String location;

    private String avator;

    private Date createTime;
}
