package com.dieudonne.supa_menu.controller;

import com.dieudonne.supa_menu.dto.RestaurantDTO;
import com.dieudonne.supa_menu.model.Restaurant;
import com.dieudonne.supa_menu.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> searchRestaurants(
            @RequestParam(required = false) String cuisineType) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(cuisineType);
        return ResponseEntity.ok(restaurants.stream().map(this::mapToRestaurantDTO).collect(Collectors.toList()));
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<RestaurantDTO>> findNearbyRestaurants(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "5.0") double radius) {
        List<Restaurant> restaurants = restaurantService.findNearByRestaurants(latitude, longitude, radius);
        return ResponseEntity.ok(restaurants.stream().map(this::mapToRestaurantDTO).collect(Collectors.toList()));
    }

    @GetMapping("/scan")
    public ResponseEntity<RestaurantDTO> findByQrCode(@RequestParam String qrCode) {
        Restaurant restaurant = restaurantService.findByQrCode(qrCode);
        return ResponseEntity.ok(mapToRestaurantDTO(restaurant));
    }

    private RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setLatitude(restaurant.getLatitude());
        dto.setLongitude(restaurant.getLongitude());
        dto.setCuisineType(restaurant.getCuisineType());
        return dto;
    }
}