/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.system;

import zjtech.bmf.api.business.smfsetting.IThemeService;
import zjtech.smf.modules.global.action.BaseAction;
import zjtech.smf.common.result.SmfResult;
import zjtech.smf.common.constants.AttributesConstants;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.api.dto.ThemeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 主题设置Action
 */
@Controller
public class ThemeAction extends BaseAction {


  @Autowired
  private IThemeService themeSettingSrv;

  /**
   * 更换主题
   *
   * @param themeName 主题名称
   * @param request   HttpServletRequest
   * @return view
   */
  @RequestMapping(value = "/theme/{themeName}", method = RequestMethod.GET)
  @ResponseBody
  public SmfResult changeTheme(@PathVariable("themeName") String themeName,
                               HttpServletRequest request) {
    SmfResult smfResult = new SmfResult();

    //在Session范围中储存当前主题的名称
    HttpSession session = getSession();
    if (!StringUtils.isEmpty(themeName) && session != null) {
      IBmfResult<ThemeDTO> result = null;//themeSettingSrv.getByName(themeName);
      ThemeDTO vo = result == null ? null : result.getAttachment();
      if (vo == null) {
        //没有对应的主题
        smfResult.setOk(false);
        String msg = getMessage("header.theme.not.exist", new
                String[]{themeName});
        smfResult.addGlobalError("theme", msg);
        return smfResult;
      } else {
        WebUtils.setSessionAttribute(request, AttributesConstants.CURRENT_THEME, vo);
      }
    }
    return smfResult;
  }

}
