/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.serverMgnt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zjtech.bmf.api.business.ICrudService;
import zjtech.bmf.api.business.serverMgnt.IMemcachedServerService;
import zjtech.bmf.api.dto.servers.MemcachedServerDTO;
import zjtech.smf.common.constants.UrlMapping;
import zjtech.smf.common.result.ISmfResult;
import zjtech.smf.modules.global.action.CrudMappingAction;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping(UrlMapping.MEMCACHED_SERVER)
public class MemcachedServerMappingAction extends CrudMappingAction<MemcachedServerDTO, Serializable> {

  @Autowired
  private IMemcachedServerService service;

  @Override
  protected ICrudService<Serializable, MemcachedServerDTO> getService() {
    return service;
  }
}
