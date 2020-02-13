package com.viktor.smd.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "auth_role_id")
  private int id;

  @Column(name = "role_name")
  private String roleName;

  @Column(name = "role_desc")
  private String roleDescription;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }
}
