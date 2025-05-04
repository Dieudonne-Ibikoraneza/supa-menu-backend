package com.dieudonne.supa_menu.repository;

import com.dieudonne.supa_menu.model.Feedback;
import com.dieudonne.supa_menu.model.Order;
import com.dieudonne.supa_menu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByOrder(Order order);
    List<Feedback> findByUser(User user);
}
