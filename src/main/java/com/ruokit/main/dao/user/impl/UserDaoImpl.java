package com.ruokit.main.dao.user.impl;

import java.util.HashMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.user.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public HashMap<String, Object> getUser(String userId) {
    return sqlSession.selectOne("User.getUser", userId);
  }
}
