/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.global.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zjtech.bmf.api.business.smfsetting.IThemeService;
import zjtech.bmf.api.business.user.IUserWebSettingService;
import zjtech.bmf.api.dto.ThemeSettingDTO;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.api.dto.user.UserWebSettingDTO;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.smf.common.SmfWebUtils;
import zjtech.smf.common.constants.UrlMapping;
import zjtech.smf.common.result.ISmfResult;
import zjtech.smf.modules.system.UserSettingForm;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;

@RestController
public class MainAction extends BaseAction {

  @Autowired
  private IUserWebSettingService smfWebSettingSrv;

  @Autowired
  private IThemeService themeSettingSrv;

  @RequestMapping(value = UrlMapping.USER_WEB_SETTING, method = RequestMethod.GET)
  @ResponseBody
  public ISmfResult<HashMap<String, ?>> getUserWebSetting() {
    HashMap<String, Object> map = new HashMap<>();
    UserDTO userDTO = getCurrentUser();

    UserWebSettingDTO webSettingVO = smfWebSettingSrv.getWebBasicSetting().getAttachment();
    if (userDTO == null) {
      map.put("currentLanguage", webSettingVO.getCurrentLanguage());
      map.put("languages", webSettingVO.getLanguages());
    } else {
      map.put("user", userDTO.toBriefMap());
      IBmfResult<ThemeSettingDTO> themeSettingResult = themeSettingSrv.getThemeSetting();
      if (themeSettingResult != null && themeSettingResult.getAttachment() != null) {
        map.put("theme", themeSettingResult.getAttachment());
      }
    }
    ISmfResult<HashMap<String, ?>> result = createResult();
    result.setAttachedObj(map);
    return result;

  }

  /**
   * 获取用户默认参数
   *
   * @return SmfResult
   */
  @RequestMapping(value = UrlMapping.INIT_SPA_USER_INIT)
  public ISmfResult<UserSettingForm> initForUser(HttpServletResponse response,
                                                 @RequestParam(value = "lan", required = false) String lan) {
    UserSettingForm form = new UserSettingForm();

    //切换语言
    SmfWebUtils.changeLanguage(response, lan);
    Locale locale = getCurrentLocale();
    String language = locale.toLanguageTag();
    form.setLanguage(language);

    UserDTO userVO = getCurrentUser();
    if (userVO != null) {
      //用户已经登陆
      form.setLoggedIn(true);
    }

    //是否开启了调试模式
    form.setDebugEnabled(false);
    ISmfResult<UserSettingForm> returnResult = createResult();
    returnResult.setAttachedObj(form).setOk(true);
    return returnResult;
  }
}
