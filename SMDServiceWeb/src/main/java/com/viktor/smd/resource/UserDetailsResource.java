package com.viktor.smd.resource;

import com.viktor.smd.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Details resource implementing the {@link UserDetails} for custom authentication.
 */
public class UserDetailsResource implements UserDetails {

  private List<GrantedAuthority> authorities;
  private String password;
  private String username;
  private boolean enabled;

  /**
   * Default constructor.
   */
  public UserDetailsResource() {
  }

  /**
   * The constructor with a mandatory parameter.
   *
   * @param user the user from db.
   */
  public UserDetailsResource(User user) {

    this.username = user.getUsername();
    this.password = user.getPassword();
    this.enabled = user.isActive();
    this.authorities = user.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
