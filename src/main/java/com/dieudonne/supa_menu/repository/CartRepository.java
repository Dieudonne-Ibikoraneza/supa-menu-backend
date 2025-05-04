package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Cart;
import com.dieudonne.supa_menu.model.Restaurant;
import com.dieudonne.supa_menu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUserAndRestaurant(User user, Restaurant restaurant);
}