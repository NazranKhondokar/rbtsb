package com.rbtsb.responses;

import com.rbtsb.entities.Role;
import com.rbtsb.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleResponse {

    private Long id;
    private RoleName roleName;

    public static RoleResponse from(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setRoleName(role.getName());
        return response;
    }
}

