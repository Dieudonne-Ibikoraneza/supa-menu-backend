package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Order;
import com.dieudonne.supa_menu.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}
