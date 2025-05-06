package com.dieudonne.supa_menu.dto;

import com.dieudonne.supa_menu.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private double totalAmount;
    private Long restaurantId;
    private List<OrderItemDTO> orderItems;
}
