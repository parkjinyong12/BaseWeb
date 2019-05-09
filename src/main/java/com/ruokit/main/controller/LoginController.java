package com.ruokit.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  @RequestMapping("/login.do")
  public String getLogin(Model m) {
    logger.info("scr. login");
    m.addAttribute("context", "test message");
    return "login";
  }
}
