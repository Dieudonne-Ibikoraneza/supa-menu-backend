package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
