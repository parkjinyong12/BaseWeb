package com.ruokit.main.test.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ruokit.main.test.dao.TestDao;

@Repository
public class TestDaoImpl implements TestDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public String getTest() {
    return sqlSession.selectOne("Test.getTest");
  }
}
