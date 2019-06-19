package com.ruokit.main.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ruokit.main.user.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    logger.info("srv. user check");

    User user = findUserbyUername(username);

    UserBuilder builder = null;
    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.password(user.getPassword());
      builder.roles(user.getRoles());
    } else {
      throw new UsernameNotFoundException("User not found.");
    }

    return builder.build();
  }

  private User findUserbyUername(String username) {


    if (username.equalsIgnoreCase("admin")) {
      return new User(username, "admin123", "ADMIN");
    }
    return null;
  }
}
