package com.ruokit.main.service.login.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruokit.main.service.login.LoginService;

@Service
public class DefaultLoginService implements LoginService {

  private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

  public String getTest() {
    logger.info("srv. getTest");
    return "";
  }
}
