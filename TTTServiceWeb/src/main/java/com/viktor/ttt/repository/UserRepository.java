package com.viktor.ttt.repository;

import com.viktor.ttt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Jpa repository for {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  List<User> findAllByUsernameIsIn(List<String> usernameList);
}
