package com.ruokit.main.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruokit.main.dao.TestDao;

@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);

  @Autowired
  private TestDao testDao;

  public String getTest() {
    logger.info("srv. getTest");
    String now = testDao.getTest();
    logger.info(now);
    return now;
  }
}
