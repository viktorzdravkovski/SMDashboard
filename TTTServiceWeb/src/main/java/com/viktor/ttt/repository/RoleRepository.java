package com.viktor.ttt.repository;

import com.viktor.ttt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa Repository for {@link Role}.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
