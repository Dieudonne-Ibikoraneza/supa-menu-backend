package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.*;
import com.dieudonne.supa_menu.repository.CartRepository;
import com.dieudonne.supa_menu.repository.OrderItemRepository;
import com.dieudonne.supa_menu.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    public Order placeOrder(Long userId, Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if(cart.getUser().getId() != userId)
            throw new IllegalArgumentException("Cart does not belong to user");

        if(cart.getCartItems().isEmpty())
            throw new IllegalArgumentException("Cart is empty");

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .user(cart.getUser())
                .restaurant(cart.getRestaurant())
                .totalAmount(cart.getTotalPrice())
                .build();

        order = orderRepository.save(order);

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(cartItem.getMenuItem())
                    .quantity(cartItem.getQuantity())
                    .build();

            orderItemRepository.save(orderItem);
        }

        cartRepository.delete(cart);
        return order;
    }

    public Page<Order> getRestaurantOrders(Long restaurantId, OrderStatus status, Pageable pageable) {
        if (status != null) {
            return orderRepository.findByRestaurantIdAndStatus(restaurantId, status, pageable);
        }
        return orderRepository.findByRestaurantId(restaurantId, pageable);
    }
}
