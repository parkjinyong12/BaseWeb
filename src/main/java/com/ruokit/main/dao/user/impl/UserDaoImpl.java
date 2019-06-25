package com.ruokit.main.dao.user.impl;

import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.CommonDao;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.model.user.User;

@Repository
public class UserDaoImpl extends CommonDao implements UserDao {

  public User getUser(String userId) {
    return getSqlSessionTemplate().selectOne("User.getUser", userId);
  }
}
