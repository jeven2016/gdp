package zjtech.bmf.api.facade;

import zjtech.bmf.api.result.IBmfResult;

/**
 * 用户门面类
 */
public interface IUserMgntFacade extends IBmfFacade {
  /**
   * 验证用户是否是合法的用户
   *
   * @param userName 用户名
   * @param password 密码
   * @return IBmfResult        I
   */
  IBmfResult login(String userName, String password);

}
