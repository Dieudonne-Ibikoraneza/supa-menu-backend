package com.dieudonne.supa_menu.controller;

import com.dieudonne.supa_menu.dto.CartDTO;
import com.dieudonne.supa_menu.dto.CartItemDTO;
import com.dieudonne.supa_menu.model.Cart;
import com.dieudonne.supa_menu.model.CartItem;
import com.dieudonne.supa_menu.service.CartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('CUSTOMER') and #userId == authentication.principal.id")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId, @RequestParam Long restaurantId) {
        Cart cart = cartService.getOrCreateCart(userId, restaurantId);
        return ResponseEntity.ok(mapToCartDTO(cart));
    }

    @PostMapping("/{userId}/add")
    @PreAuthorize("hasRole('CUSTOMER') and #userId == authentication.principal.id")
    public ResponseEntity<CartDTO> addItemToCart(
            @PathVariable Long userId,
            @RequestBody AddCartItemRequest request) {
        Cart cart = cartService.addItemToCart(
                userId,
                request.getRestaurantId(),
                request.getMenuItemId(),
                request.getQuantity());
        return ResponseEntity.ok(mapToCartDTO(cart));
    }

    private CartDTO mapToCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setRestaurantId(cart.getRestaurant().getId());
        dto.setTotalPrice(cart.getTotalPrice());
        dto.setItems(cart.getCartItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList()));
        return dto;
    }

    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setMenuItemId(cartItem.getMenuItem().getId());
        dto.setMenuItemName(cartItem.getMenuItem().getName());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getMenuItem().getPrice());
        return dto;
    }
}

@Data
class AddCartItemRequest {
    private Long restaurantId;
    private Long menuItemId;
    private int quantity;
    // Getters and setters
}