package spring.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.model.user.LoginUser;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

  @Autowired
  UserDao userDao;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
      Authentication auth) throws IOException, ServletException {

    HttpSession session = req.getSession();

    LoginUser userInfo = (LoginUser) auth.getPrincipal();

    String userId = userInfo.getUsername();
    // UserInfo userInfo = userDao.getUser(userId);

    if (session != null) {
      session.setAttribute("loginId", userId);
      session.setAttribute("userInfo", userInfo);
    }

    logger.info("srv. login success handler");
    super.onAuthenticationSuccess(req, resp, auth);
  }
}
