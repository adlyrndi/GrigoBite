package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    User findById(long id);
}
