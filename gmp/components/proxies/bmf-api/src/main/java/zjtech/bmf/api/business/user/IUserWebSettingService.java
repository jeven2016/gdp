/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.business.user;

import zjtech.bmf.api.business.IBaseService;
import zjtech.bmf.api.dto.user.UserWebSettingDTO;
import zjtech.bmf.api.result.IBmfResult;


public interface IUserWebSettingService extends IBaseService {
  IBmfResult<UserWebSettingDTO> getWebBasicSetting();
}
