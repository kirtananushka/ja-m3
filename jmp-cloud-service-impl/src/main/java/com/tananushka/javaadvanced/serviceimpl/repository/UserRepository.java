package com.tananushka.javaadvanced.serviceimpl.repository;

import com.tananushka.javaadvanced.serviceimpl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}