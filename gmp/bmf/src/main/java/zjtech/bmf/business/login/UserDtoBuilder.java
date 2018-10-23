/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.components.builder.IDtoBuilder;
import zjtech.bmf.components.convert.IBeanConvertor;
import zjtech.dmf.domain.adminuser.UserEntity;

@Component
public class UserDtoBuilder implements IDtoBuilder<UserDTO, UserEntity> {

  @Autowired
  private IBeanConvertor convert;

  @Override
  public UserDTO build(UserEntity entity) {
    UserDTO dto = new UserDTO();
    convert.convert(entity, dto);
    return dto;
  }
}
