package com.ruokit.main.model.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

  private String userId;
  private String password;

  private boolean isAccountNonExpired;
  private String[] roles;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;

  public User(String username, String password, boolean isAccountNonExpired,
      boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
    this.userId = username;
    this.password = password;
    this.isAccountNonExpired = isAccountNonExpired;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isCredentialsNonExpired = isCredentialsNonExpired;
    this.isEnabled = isEnabled;
  }

  // Getter and Setter methods
  public String getUsername() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public String[] getRoles() {
    return roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }
}
