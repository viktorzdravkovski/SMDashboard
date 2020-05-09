package com.viktor.ttt.repository;

import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository for {@link Role}.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  /**
   * Gets the role by its role name.
   *
   * @param roleName the role name.
   * @return {@link Role}.
   */
  Role getRoleByRoleName(RoleType roleName);
}
