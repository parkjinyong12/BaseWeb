package com.ruokit.main.controller.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruokit.main.service.main.MainService;

@Controller
public class MainController {

  private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  @Autowired
  MainService mainService;

  @RequestMapping("/main.do")
  public String getLogin() {
    logger.info("scr. main");
    return "main";
  }
}
