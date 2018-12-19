package com.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private String enable;

    private String account;

    private String credentials;

    private String locked;

    private Date createTime;

    private Date lastTime;

    private String salt;

}