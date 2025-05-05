package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.Order;
import com.dieudonne.supa_menu.model.Restaurant;
import com.dieudonne.supa_menu.repository.OrderRepository;
import com.dieudonne.supa_menu.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public double getTotalSales(Long restaurantId, LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderRepository.findByRestaurantIdAndOrderDateBetween(restaurantId, start, end);

        return orders.stream().mapToDouble(Order::getTotalAmount).sum();
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long restaurantId){
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
    }
}
