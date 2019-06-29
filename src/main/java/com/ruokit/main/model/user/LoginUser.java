package com.ruokit.main.model.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

  public LoginUser(String username, String password, boolean isAccountNonExpired,
      boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired,
        isEnabled, authorities);
  }

}
