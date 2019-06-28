package com.ruokit.main.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruokit.main.controller.test.TestController;
import com.ruokit.main.service.login.LoginService;

@Controller
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  LoginService loginService;

  @RequestMapping("/login.do")
  public String getLogin() {
    logger.info("scr. login");
    return "login";
  }

  @RequestMapping("/loginFail.do")
  public String getLoginFail() {
    logger.info("scr. loginFail");
    return "loginFail";
  }

  @RequestMapping("/loginSuccess.do")
  public String getLoginSuccess() {
    logger.info("scr. loginSuccess");
    loginService.getTest();
    return "main";
  }
}
