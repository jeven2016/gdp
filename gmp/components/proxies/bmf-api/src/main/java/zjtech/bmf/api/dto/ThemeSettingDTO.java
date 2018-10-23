/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.dto;

import zjtech.common.result.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题设置对象
 */
public class ThemeSettingDTO extends BaseDTO {
  //默认主题
  private ThemeDTO defaultTheme;

  //可选主题列表
  private List<ThemeDTO> themesList = new ArrayList();

  public ThemeDTO getDefaultTheme() {
    return defaultTheme;
  }

  public void setDefaultTheme(ThemeDTO defaultTheme) {
    this.defaultTheme = defaultTheme;
  }

  public List<ThemeDTO> getThemesList() {
    return themesList;
  }

  public void setThemesList(List<ThemeDTO> themesList) {
    this.themesList = themesList;
  }
}
