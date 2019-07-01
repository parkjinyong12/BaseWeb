package com.ruokit.main.dao.user.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruokit.main.dao.CommonDao;
import com.ruokit.main.dao.user.UserDao;
import com.ruokit.main.model.user.UserInfo;
import com.ruokit.main.model.user.UserRole;

@Repository
public class DefaultUserDao extends CommonDao implements UserDao {

  @Override
  public UserInfo getUser(String userId) {
    return getSqlSessionTemplate().selectOne("User.getUser", userId);
  }

  @Override
  public List<UserRole> getUserRoles(String userId) {
    return getSqlSessionTemplate().selectList("User.getUserRole", userId);
  }
}
