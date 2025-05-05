package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.model.Category;
import com.dieudonne.supa_menu.model.MenuItem;
import com.dieudonne.supa_menu.repository.CategoryRepository;
import com.dieudonne.supa_menu.repository.MenuItemRepository;
import com.dieudonne.supa_menu.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public List<MenuItem> getMenuItems(Long restaurantId, Long categoryId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if(categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            return menuItemRepository.findByRestaurantIdAndCategory(restaurantId, category);
        }

        return menuItemRepository.findByRestaurantIdAndIsAvailable(restaurantId, true);
    }

    public MenuItem createMenuItem(Long restaurantId, Long categoryId, MenuItem menuItem) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        menuItem.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        menuItem.setCategory(category);
        menuItem.setAvailable(true);

        return menuItemRepository.save(menuItem);
    }
}
