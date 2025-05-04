package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByQrCode(String qrCode);
    List<Restaurant> findByCuisineType(String cuisineType);

    @Query("SELECT r FROM Restaurant r WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * " +
            "cos(radians(r.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(r.latitude))) < :radius")
    List<Restaurant> findByLocationNear(double latitude, double longitude, double radius);
}
