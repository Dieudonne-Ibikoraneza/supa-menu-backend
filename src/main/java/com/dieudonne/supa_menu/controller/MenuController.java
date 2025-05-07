package com.dieudonne.supa_menu.controller;

import com.dieudonne.supa_menu.dto.MenuItemDTO;
import com.dieudonne.supa_menu.model.MenuItem;
import com.dieudonne.supa_menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getMenuItems(
            @RequestParam Long restaurantId,
            @RequestParam(required = false) Long categoryId) {
        List<MenuItem> menuItems = menuService.getMenuItems(restaurantId, categoryId);
        return ResponseEntity.ok(menuItems.stream().map(this::mapToMenuItemDTO).collect(Collectors.toList()));
    }

    private MenuItemDTO mapToMenuItemDTO(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setCategoryId(menuItem.getCategory().getId());
        return dto;
    }
}