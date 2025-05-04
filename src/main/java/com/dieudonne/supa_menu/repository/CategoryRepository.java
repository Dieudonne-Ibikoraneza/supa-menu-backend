package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Category;
import com.dieudonne.supa_menu.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRestaurant(Restaurant restaurant);
}