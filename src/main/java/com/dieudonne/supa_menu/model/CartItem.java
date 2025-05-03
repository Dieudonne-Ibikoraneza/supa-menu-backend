package com.dieudonne.supa_menu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name="cart_items")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    @OneToMany(mappedBy = "menu_item_id")
    private List<MenuItem> menuItems;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
