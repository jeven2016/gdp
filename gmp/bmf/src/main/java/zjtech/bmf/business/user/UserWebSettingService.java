/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.user;

import org.springframework.stereotype.Service;
import zjtech.bmf.api.business.user.IUserWebSettingService;
import zjtech.bmf.api.dto.user.UserWebSettingDTO;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;

@Service
public class UserWebSettingService implements IUserWebSettingService {

  @Override
  public IBmfResult<UserWebSettingDTO> getWebBasicSetting() {
    IBmfResult<UserWebSettingDTO> result = new BmfResult<>(true, new UserWebSettingDTO());
    return result;
  }
}
