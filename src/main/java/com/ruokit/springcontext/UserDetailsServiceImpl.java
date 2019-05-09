package com.ruokit.springcontext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    logger.debug("UserDetailsService loadUserByUsername");
    /*
     * Here we are using dummy data, you need to load user data from database or other third party
     * application
     */
    User user = findUserbyUername(username);

    UserBuilder builder = null;
    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
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
