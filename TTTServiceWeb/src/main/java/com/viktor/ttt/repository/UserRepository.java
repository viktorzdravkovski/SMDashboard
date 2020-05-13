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

  /**
   * Find an optional user by his username.
   *
   * @param username the username.
   * @return an optional of {@link User}.
   */
  Optional<User> findByUsername(String username);

  /**
   * Get a user by his username.
   *
   * @param username the username.
   * @return {@link User}.
   */
  User getByUsername(String username);

  /**
   * Get all users from a list of usernames.
   *
   * @param usernameList the list of usernames.
   * @return a list of {@link User}.
   */
  List<User> findAllByUsernameIsIn(List<String> usernameList);
}
