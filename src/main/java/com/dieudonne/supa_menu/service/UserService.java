package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.User;
import com.dieudonne.supa_menu.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User updateUser(Long userId, String fullName, String phoneNumber) {
        User user = getUserById(userId);

        if(fullName != null && !fullName.isEmpty())
            user.setFullName(fullName);

        if(phoneNumber != null && !phoneNumber.isEmpty()) {
            if (userRepository.existsByPhoneNumber(phoneNumber) && phoneNumber.equals(user.getPhoneNumber()))
                throw new IllegalArgumentException("Phone number already exists");
            user.setPhoneNumber(phoneNumber);
        }
        return userRepository.save(user);
    }

    
}
