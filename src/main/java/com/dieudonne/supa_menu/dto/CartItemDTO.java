package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String menuItemName;
    private Long menuItemId;
    private double price;
    private int quantity;
}
