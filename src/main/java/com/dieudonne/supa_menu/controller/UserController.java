package com.dieudonne.supa_menu.controller;

import com.dieudonne.supa_menu.dto.UpdateUserRequest;
import com.dieudonne.supa_menu.dto.UserDTO;
import com.dieudonne.supa_menu.model.User;
import com.dieudonne.supa_menu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') and #id == authentication.principal.id")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(mapToUserDTO(user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') and #id == authentication.principal.id")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        User user = userService.updateUser(id, request.getFullName(), request.getPhoneNumber());
        return ResponseEntity.ok(mapToUserDTO(user));
    }


    private UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());

        return dto;
    }
}
