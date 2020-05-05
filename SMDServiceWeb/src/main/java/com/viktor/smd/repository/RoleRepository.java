package com.viktor.smd.repository;

import com.viktor.smd.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa Repository for {@link Role}.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
