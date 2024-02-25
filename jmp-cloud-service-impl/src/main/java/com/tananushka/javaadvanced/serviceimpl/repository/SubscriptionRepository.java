package com.tananushka.javaadvanced.serviceimpl.repository;

import com.tananushka.javaadvanced.serviceimpl.entity.Subscription;
import com.tananushka.javaadvanced.serviceimpl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    void deleteAllByUser(User user);
}