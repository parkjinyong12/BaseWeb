package com.ruokit.main.service.main.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruokit.main.service.main.MainService;

@Service
public class MainServiceImpl implements MainService {

  private static final Logger logger = LoggerFactory.getLogger(MainService.class);

  public String getMainPage() {
    return "main";
  }
}
