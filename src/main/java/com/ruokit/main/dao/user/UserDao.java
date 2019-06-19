package com.ruokit.main.dao.user;

import java.util.HashMap;

public interface UserDao {
  public HashMap<String, Object> getUser(String userId);
}
