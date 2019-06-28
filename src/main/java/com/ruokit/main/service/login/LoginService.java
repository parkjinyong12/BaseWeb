package com.ruokit.main.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

  public String getTest() {
    logger.info("srv. getTest");
    return "";
  }
}
