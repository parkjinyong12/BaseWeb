package com.ruokit.main.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruokit.main.dao.TestDao;
import com.ruokit.main.service.mail.MailService;

@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(MailService.class);

  @Autowired
  private TestDao testDao;

  public void getTest() {
    String now = testDao.getTest();
    logger.debug(now);
  }
}
