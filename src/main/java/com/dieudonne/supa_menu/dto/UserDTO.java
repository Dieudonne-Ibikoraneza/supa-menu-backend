package com.dieudonne.supa_menu.dto;

import com.dieudonne.supa_menu.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
