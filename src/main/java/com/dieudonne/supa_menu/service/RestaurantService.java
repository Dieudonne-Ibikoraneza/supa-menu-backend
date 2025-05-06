package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.Restaurant;
import com.dieudonne.supa_menu.repository.RestaurantRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> searchRestaurants(String cuisineType){
        if(cuisineType != null)
            return restaurantRepository.findByCuisineType(cuisineType);

        return restaurantRepository.findAll();
    }

    public List<Restaurant> findNearByRestaurants(double latitude, double longitude, double radiusKm){
        return restaurantRepository.findByLocationNear(latitude, longitude, radiusKm);
    }

    public Restaurant findByQrCode(String qrCode){
        return restaurantRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found for the given QR code"));
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        if (restaurantRepository.existsByEmail(restaurant.getEmail()))
            throw new EntityExistsException("Restaurant already exists");

        restaurant.setCreatedAt(LocalDateTime.now());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (updatedRestaurant.getName() != null) restaurant.setName(updatedRestaurant.getName());
        if (updatedRestaurant.getAddress() != null) restaurant.setAddress(updatedRestaurant.getAddress());
        if (updatedRestaurant.getPhone() != null) restaurant.setPhone(updatedRestaurant.getPhone());
        if (updatedRestaurant.getEmail() != null) restaurant.setEmail(updatedRestaurant.getEmail());
        if (updatedRestaurant.getLatitude() != null) restaurant.setLatitude(updatedRestaurant.getLatitude());
        if (updatedRestaurant.getLongitude() != null) restaurant.setLongitude(updatedRestaurant.getLongitude());
        if (updatedRestaurant.getCuisineType() != null) restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        if (updatedRestaurant.getQrCode() != null) restaurant.setQrCode(updatedRestaurant.getQrCode());
        if (updatedRestaurant.getRepresentativeName() != null) restaurant.setRepresentativeName(updatedRestaurant.getRepresentativeName());
        if (updatedRestaurant.getBankAccount() != null) restaurant.setBankAccount(updatedRestaurant.getBankAccount());
        if (updatedRestaurant.getLogoUrl() != null) restaurant.setLogoUrl(updatedRestaurant.getLogoUrl());
        if (updatedRestaurant.getOpeningHours() != null) restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        if (updatedRestaurant.getClosingHours() != null) restaurant.setClosingHours(updatedRestaurant.getClosingHours());

        return restaurantRepository.save(restaurant);
    }

}
