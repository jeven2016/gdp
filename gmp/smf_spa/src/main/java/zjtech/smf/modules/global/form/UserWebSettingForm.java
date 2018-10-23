/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.global.form;


import zjtech.bmf.api.dto.ThemeSettingDTO;
import zjtech.bmf.api.dto.setting.LanguageDTO;

import java.util.ArrayList;
import java.util.List;

public class UserWebSettingForm extends BaseForm {
  private String currentLanguage;

  //可选的语言列表
  private List<LanguageDTO> languages = new ArrayList<>();

  private ThemeSettingDTO theme;


}
