
/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.login;

import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zjtech.bmf.api.business.user.IUserLoginService;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.smf.common.SmfWebUtils;
import zjtech.smf.common.annotation.AuditLog;
import zjtech.smf.common.constants.AttributesConstants;
import zjtech.smf.common.constants.UrlMapping;
import zjtech.smf.common.constants.SmfValueConstants;
import zjtech.smf.common.constants.ViewNameConstants;
import zjtech.smf.common.exception.SmfException;
import zjtech.smf.common.result.ISmfResult;
import zjtech.smf.modules.global.action.BaseAction;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserLoginAction extends BaseAction {

  @Autowired
  private IUserLoginService userLoginService;

  /**
   * 用户登录
   *
   * @return 登录验证后的页面
   */
  @AuditLog(key = "admin.login", successErrorCode = ViewNameConstants.MAIN_PAGE)
  @RequestMapping(value = UrlMapping.USER_LOGIN, produces = "application/json")
  public ISmfResult login(@Valid @RequestBody UserLoginForm userLoginForm,
                          BindingResult result) {
    ISmfResult smfResult = createResult(result);
    try {
      if (result.hasErrors()) {
        throw new SmfException(smfResult);
      }

      //判断是否启用了验证码校验
      boolean isVerifyCodeEnabled = false;
      Object ctxVerifyCode = SmfWebUtils.getServletContext().getAttribute(AttributesConstants.ENABLE_VERIFYCODE_FORADMINLOGIN);
      if (ctxVerifyCode != null) {
        isVerifyCodeEnabled = Boolean.parseBoolean(ctxVerifyCode.toString());
      }

      //当验证码功能时已启用时，进行验证码校验
      if (isVerifyCodeEnabled) {
        String verifyCode = userLoginForm.getVerifyCode();

        //校验验证码是否有效
        if (StringUtils.isEmpty(verifyCode) || verifyCode.length() > SmfValueConstants.MAX_NUMBER_OF_VERIFY_CODE) {
          smfResult.addFieldErrorByKey("verifyCode", "login.verifyCode.length");
          throw new SmfException(smfResult);
        }

        //校验验证码是否匹配
        String key = (String) getFromSessionByAttr(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isEmpty(key) || !key.toLowerCase().equals(verifyCode.toLowerCase())) {
          smfResult.addFieldErrorByKey("verifyCode", "login.verifyCode.invalid");
          throw new SmfException(smfResult);
        }
      }

      //登录验证
      IBmfResult returnResult = userLoginService.login(userLoginForm.getUserName(),
              userLoginForm.getPassword());
      smfResult = retrieveSmfResult(returnResult, smfResult);
      if (smfResult.isOk()) {
        //将该用户VO保存至Session范围
        UserDTO userVO = (UserDTO) returnResult.getAttachment();
        getSession(true).setAttribute(AttributesConstants.CURRENT_USER, userVO);
      }
    } catch (SmfException smfExp) {
      return smfExp.getResult();
    } catch (Exception e) {
      smfResult.addGlobalError("globalError", "internal.exception", e);
    }

    userLoginForm.setPassword(null); //clear password
    smfResult.setAttachedObj(userLoginForm);
    return smfResult;
  }


  /**
   * 用户登录初始化
   *
   * @return
   */
  @RequestMapping(value = UrlMapping.USER_LOGOUT)
  public ISmfResult logout() {
    HttpSession session = getSession();
    if (session != null) {
      getSession().invalidate();
    }
    return createResult().setValidSession(false);
  }

}
