/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.dto.user;

import zjtech.bmf.api.dto.setting.LanguageDTO;
import zjtech.common.result.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class UserWebSettingDTO extends BaseDTO {

  //当前生效的语言
  private String currentLanguage;

  //可选的语言列表
  private List<LanguageDTO> languages = new ArrayList<>();

  //对管理员登录是否启用验证码
  private boolean enableVerifyCodeForAdminLogin = false;

  public boolean isEnableVerifyCodeForAdminLogin() {
    return enableVerifyCodeForAdminLogin;
  }

  public void setEnableVerifyCodeForAdminLogin(boolean enableVerifyCodeForAdminLogin) {
    this.enableVerifyCodeForAdminLogin = enableVerifyCodeForAdminLogin;
  }

  public String getCurrentLanguage() {
    return currentLanguage;
  }

  public void setCurrentLanguage(String currentLanguage) {
    this.currentLanguage = currentLanguage;
  }

  public List<LanguageDTO> getLanguages() {
    return languages;
  }

  public void setLanguages(List<LanguageDTO> languages) {
    this.languages = languages;
  }
}
