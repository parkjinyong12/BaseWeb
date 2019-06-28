package com.ruokit.main.dao.user;

import java.util.List;
import com.ruokit.main.model.user.UserInfo;
import com.ruokit.main.model.user.UserRole;

public interface UserDao {

  public UserInfo getUser(String userId);

  public List<UserRole> getUserRoles(String userId);
}
