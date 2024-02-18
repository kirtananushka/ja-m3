package com.tananushka.javaadvance.jmp.cloud.service.impl.repository;

import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}