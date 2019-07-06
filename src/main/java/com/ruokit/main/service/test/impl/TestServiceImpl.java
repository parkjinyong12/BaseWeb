package com.ruokit.main.service.test.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruokit.main.dao.test.TestDao;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.service.test.TestService;

@Service
public class TestServiceImpl implements TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);

  @Autowired
  private TestDao testDao;

  @Autowired
  private UserDao userDao;

  public String getTest() {
    String now = testDao.getTest();
    userDao.getUser("admin");
    // logger.info("query result : " + now);
    return now;
  }

  public String saveTest() {
    String now = testDao.getTest();
    // logger.info("result : " + now);
    return now;
  }

}
