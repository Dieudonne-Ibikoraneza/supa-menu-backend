package com.dieudonne.supa_menu.dto;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String cuisineType;
}
