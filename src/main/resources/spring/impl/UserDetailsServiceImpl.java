package spring.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.model.user.UserInfo;
import com.ruokit.main.model.user.UserRole;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Autowired
  UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    logger.info("srv. user check");
    // logger.info(username);

    UserInfo user = findUserbyUername(username);

    UserBuilder builder = null;
    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.username(user.getUserId());

      // 임시로 패스워드를 인코딩합니다. 추후, DB에 암호화된 비밀번호를 저장하도록 수정.
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      builder.password(encoder.encode(user.getPasswd()));
      builder.accountLocked(false);
      builder.accountExpired(false);
      builder.credentialsExpired(false);
      builder.disabled(false);
      builder.authorities(user.getUserRoles());
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }

  private UserInfo findUserbyUername(String username) {
    UserInfo user = userDao.getUser(username);
    List<UserRole> userRoleList = userDao.getUserRoles(username);
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    userRoleList.forEach(userRole -> {
      authorities.add(new SimpleGrantedAuthority(userRole.getRoleCd()));
    });
    user.setUserRoles(authorities);
    return user;
  }
}
