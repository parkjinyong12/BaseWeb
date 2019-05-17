package com.ruokit.main.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.TestDao;

@Repository
public class TestDaoImpl implements TestDao {

  @Autowired
  private SqlSession sqlSession;

  public String getTest() {
    return sqlSession.selectOne("Test.getTest");
  }
}
