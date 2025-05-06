package com.dieudonne.supa_menu.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private Long restaurantId;
    private double totalPrice;
    private List<CartItemDTO> items;
}
