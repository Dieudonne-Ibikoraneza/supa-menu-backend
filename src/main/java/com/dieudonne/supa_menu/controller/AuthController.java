package com.dieudonne.supa_menu.controller;

import com.dieudonne.supa_menu.dto.AuthResponseDTO;
import com.dieudonne.supa_menu.dto.LoginRequest;
import com.dieudonne.supa_menu.dto.RegisterRequest;
import com.dieudonne.supa_menu.dto.UserDTO;
import com.dieudonne.supa_menu.model.Role;
import com.dieudonne.supa_menu.model.User;
import com.dieudonne.supa_menu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegisterRequest request) {
        User user = authService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword(),
                Role.CUSTOMER);
        String jwt = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(buildAuthResponse(user, jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequest request
    ) {
        String jwt = authService.login(request.getEmail(), request.getPassword());
        User user = authService.getUserByEmail(request.getEmail());

        return ResponseEntity.ok(buildAuthResponse(user, jwt));
    }


    private AuthResponseDTO buildAuthResponse(User user, String jwt) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setUser(userDTO);
        response.setJwt(jwt);

        return response;
    }
}
