package com.ruokit.main.dao.test.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.test.TestDao;

@Repository
public class DefaultTestDao implements TestDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public String getTest() {
    return sqlSession.selectOne("Test.getTest");
  }
}
