package zjtech.bmf.api.business.user;

import zjtech.bmf.api.business.IBaseService;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.api.result.IBmfResult;

/**
 * 用户登陆业务类
 */
public interface IUserLoginService extends IBaseService {
  /**
   * 登录
   *
   * @param userName 用户名
   * @param password 密码
   * @return IBmfResult<UserDTO>
   */
  IBmfResult<UserDTO> login(String userName, String password);
}
