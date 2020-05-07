package com.viktor.ttt.model;

import javax.persistence.*;

/**
 * Hibernate entity for AUTH_ROLE.
 */
@Entity
@Table(name = "AUTH_ROLE")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AUTH_ROLE_ID")
  private int id;

  @Column(name = "ROLE_NAME")
  @Enumerated(EnumType.STRING)
  private RoleType roleName;

  @Column(name = "ROLE_DESCRIPTION")
  private String roleDescription;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RoleType getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleType roleName) {
    this.roleName = roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }
}
