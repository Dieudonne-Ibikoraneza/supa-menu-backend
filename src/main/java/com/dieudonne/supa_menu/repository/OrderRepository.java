package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Order;
import com.dieudonne.supa_menu.model.OrderStatus;
import com.dieudonne.supa_menu.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    Page<Order> findByRestaurantId(Long restaurantId, Pageable pageable);
    Page<Order> findByRestaurantIdAndStatus(long restaurantId, OrderStatus status, Pageable pageable);
    List<Order> findByRestaurantIdAndOrderDateBetween(long restaurantId, LocalDateTime start, LocalDateTime end);
}
