package com.rbtsb.dto.auth;


import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    private String mobileOrEmail;

    @NotBlank
    private String password;

    public String getMobileOrEmail() {
        return mobileOrEmail;
    }

    public void setMobileOrEmail(String mobileOrEmail) {
        this.mobileOrEmail = mobileOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}