package com.rbtsb.responses;

import com.rbtsb.entities.Role;
import com.rbtsb.entities.User;
import lombok.Data;

import java.util.Set;

import static com.rbtsb.constant.ResponseStatus.SUCCESS;

@Data
public class LoginResponse {

    private String status;
    private String userName;
    private String email;
    private Set<Role> roles;

    public static LoginResponse from(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.getEmail());
        loginResponse.setStatus(SUCCESS);
        loginResponse.setUserName(user.getUserName());
        loginResponse.setRoles(user.getRoles());
        return loginResponse;
    }
}
