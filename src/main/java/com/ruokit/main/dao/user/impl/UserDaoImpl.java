package com.ruokit.main.dao.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.user.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public String getUser() {
    return sqlSession.selectOne("Test.getTest");
  }
}
