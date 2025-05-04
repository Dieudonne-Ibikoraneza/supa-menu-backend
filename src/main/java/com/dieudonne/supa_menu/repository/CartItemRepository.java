package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Cart;
import com.dieudonne.supa_menu.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
}