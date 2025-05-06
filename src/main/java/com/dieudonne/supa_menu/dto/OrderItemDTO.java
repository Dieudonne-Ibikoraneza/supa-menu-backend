package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private int quantity;
    private double price;
}
