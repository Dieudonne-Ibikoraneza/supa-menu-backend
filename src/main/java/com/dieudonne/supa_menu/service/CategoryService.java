package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.Category;
import com.dieudonne.supa_menu.model.Restaurant;
import com.dieudonne.supa_menu.repository.CategoryRepository;
import com.dieudonne.supa_menu.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Category> getCategoriesByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        return categoryRepository.findByRestaurant(restaurant);
    }

    public Category createCategory(Long restaurantId, String name){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Category category = Category.builder()
                .name(name)
                .restaurant(restaurant)
                .build();

        return categoryRepository.save(category);
    }
}
