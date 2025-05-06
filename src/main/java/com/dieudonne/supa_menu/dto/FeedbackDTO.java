package com.dieudonne.supa_menu.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackDTO {
    private Long id;
    private String comment;
    private int rating;
    private Long orderId;
    private LocalDateTime createdAt;
}
