package zjtech.bmf.business.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjtech.bmf.api.business.user.IUserLoginService;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.api.result.BmfErrorCode;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.components.builder.IDtoBuilder;
import zjtech.common.util.DateTimeUtlil;
import zjtech.common.util.SecurityUtil;
import zjtech.dmf.domain.adminuser.UserEntity;
import zjtech.dmf.repository.user.UserRep;


/**
 * 管理员登陆业务类
 */
@Service
@Transactional
public class UserLoginService implements IUserLoginService {
  @Autowired
  private UserRep userRep;

  @Autowired
  private IDtoBuilder<UserDTO, UserEntity> userBuilder;

  @Override
  public IBmfResult<UserDTO> login(String userName, String password) {
    UserEntity user = userRep.findByName(userName);
    if (user == null) {
      //用户不存在
      return new BmfResult(BmfErrorCode.USER_NOT_EXIST);
    }
    if (user.isLocked()) {
      //用户被锁定
      return new BmfResult(BmfErrorCode.ACCOUNT_LOCKED);
    }

    String pwd = user.getPassword();
    String encodedPwd = SecurityUtil.getMD5String(password);

    //密码不正确
    if (pwd != null && !pwd.equals(encodedPwd)) {
      return new BmfResult(BmfErrorCode.INVALID_CREDENTIAL);
    }

    //更新上次登陆时间
    userRep.updateLastLoginTime(user.getId(), DateTimeUtlil.getCurrentDate());

    UserDTO vo = userBuilder.build(user);
    vo.setPassword(null);
    return new BmfResult(true, vo);
  }
}
