package com.viktor.smd.repository;

import com.viktor.smd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Jpa repository for {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
}
