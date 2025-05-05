package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.Feedback;
import com.dieudonne.supa_menu.model.Order;
import com.dieudonne.supa_menu.model.User;
import com.dieudonne.supa_menu.repository.FeedbackRepository;
import com.dieudonne.supa_menu.repository.OrderRepository;
import com.dieudonne.supa_menu.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Feedback submitFeedback(Long userId, Long orderId, int rating, String comment) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getUser().getId() != userId)
            throw new IllegalArgumentException("Order does not belong to user");

        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        Feedback feedback = Feedback.builder()
                .user(user)
                .order(order)
                .comment(comment)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();

        return feedbackRepository.save(feedback);
    }
}
