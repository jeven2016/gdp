package zjtech.bmf.facadeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjtech.bmf.api.facade.IUserMgntFacade;
import zjtech.bmf.api.result.BmfErrorCode;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.api.business.user.IUserLoginService;

/**
 * 用户门面
 */
@Component
public class AdminUserMgntFacadeImpl implements IUserMgntFacade {
  IUserLoginService userLoginService;

  @Autowired
  public void setUserLoginService(IUserLoginService service) {
    this.userLoginService = service;
  }

  @Override
  public IBmfResult login(String userName, String password) {
    IBmfResult result;
    try {
      result = userLoginService.login(userName, password);
    } catch (Exception e) {
      e.printStackTrace();
      result = new BmfResult(BmfErrorCode.INTERNAL_EXCEPTION);
    }
    return result;
  }
}
