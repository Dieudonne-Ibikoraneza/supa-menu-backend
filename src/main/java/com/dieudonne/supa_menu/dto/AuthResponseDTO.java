package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String jwt;
    private UserDTO user;
}
