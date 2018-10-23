/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.dto.user;

import zjtech.common.result.BaseDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户值对象
 */
public class UserDTO extends BaseDTO {
  private String name;
  private String password;
  private String description;
  private boolean pwdChanged;
  private Date lastLogin;
  private boolean locked;

  private Map<String, Object> map;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPwdChanged() {
    return pwdChanged;
  }

  public void setPwdChanged(boolean pwdChanged) {
    this.pwdChanged = pwdChanged;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public Map<String, Object> toBriefMap() {
    if (map == null) {
      map = new HashMap<>();
      map.put("name", name);
      map.put("description", description);
      map.put("lastLogin", lastLogin);
      map.put("pwdChanged", pwdChanged);
      map.put("locked", locked);
    }
    return map;
  }
}
