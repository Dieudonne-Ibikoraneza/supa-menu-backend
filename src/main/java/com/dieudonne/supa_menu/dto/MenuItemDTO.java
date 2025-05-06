package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Long categoryId;
}
