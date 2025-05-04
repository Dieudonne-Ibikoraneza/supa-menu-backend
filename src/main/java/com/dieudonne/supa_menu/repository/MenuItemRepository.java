package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Category;
import com.dieudonne.supa_menu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(long restaurantId);
    List<MenuItem> findByRestaurantIdAndCategory(long restaurantId, Category category);
    List<MenuItem> findByRestaurantIdAndIsAvailable(long restaurantId, boolean isAvailable);
}
