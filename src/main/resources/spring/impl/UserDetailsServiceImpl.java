package spring.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.model.user.LoginUser;
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
    UserInfo userInfo = findUserbyUername(username);

    boolean accountNonLocked = true;
    boolean accountNonExpired = true;
    boolean enabled = true;

    String userId = null;
    String encodingPassword = null;

    if (userInfo != null) {
      userId = userInfo.getUserId();

      if ("Y".equals(userInfo.getAcntYn())) {
        enabled = false;
        logger.info("미 사용 계정입니다. 사용자 아이디 : " + userId);
      } else if ("Y".equals(userInfo.getAcntLockYn())) {
        accountNonLocked = false;
        logger.info("특정사유(비밀번호 입력회수 초과 등)로 인해 잠긴 계정입니다. 사용자 아이디 : " + userId);
      } else if ("Y".equals(userInfo.getAcntExpYn())) {
        accountNonExpired = false;
        logger.info("사용기한이 만료된 계정입니다. 사용자 아이디 : " + userId);
      }

      // 임시로 패스워드를 인코딩합니다. 추후, DB에 암호화된 비밀번호를 저장하도록 수정.
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      encodingPassword = encoder.encode(userInfo.getPasswd());

    } else {
      throw new UsernameNotFoundException("User not found.");
    }

    LoginUser loginUser = new LoginUser(userId, encodingPassword, enabled, accountNonExpired, true,
        accountNonLocked, userInfo.getUserRoles());

    return loginUser;
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
