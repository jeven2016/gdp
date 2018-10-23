/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.components.builder;


import zjtech.common.result.IBaseDTO;
import zjtech.dmf.domain.IDEntity;

public interface IDtoBuilder<DTO extends IBaseDTO, Entity extends IDEntity> {
  DTO build(Entity entity);
}
