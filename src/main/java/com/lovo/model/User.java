package com.lovo.model;

import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userGrade;

    public User() {
    }

    public User(String userAccount, String userPassword, String userName, String userGrade) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userGrade = userGrade;
    }
}