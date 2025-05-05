package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.*;
import com.dieudonne.supa_menu.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public Cart getOrCreateCart(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        return cartRepository.findByUserAndRestaurant(user, restaurant)
                .orElseGet(() -> {
                    Cart cart = Cart.builder()
                            .user(user)
                            .restaurant(restaurant)
                            .totalPrice(0.0)
                            .build();
                    return cartRepository.save(cart);
                });
    }

    public Cart addItemToCart(Long userId, Long restaurantId, Long menuItemId, int quantity) {
        Cart cart = getOrCreateCart(userId, restaurantId);
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
        if (!menuItem.isAvailable()) {
            throw new IllegalArgumentException("Menu item is not available");
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getMenuItem().getId() == menuItemId)
                .findFirst()
                .orElse(CartItem.builder()
                        .cart(cart)
                        .menuItem(menuItem)
                        .quantity(0)
                        .build());

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);

        cart.setTotalPrice(calculateTotalPrice(cart));
        return cartRepository.save(cart);
    }

    private double calculateTotalPrice(Cart cart) {
        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
    }
}