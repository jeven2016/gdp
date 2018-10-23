/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.serverMgnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zjtech.bmf.api.business.serverMgnt.IMemcachedServerService;
import zjtech.bmf.api.dto.servers.MemcachedServerDTO;
import zjtech.bmf.business.global.BaseCrudService;
import zjtech.dmf.domain.servers.MemcachedServerEntity;
import zjtech.dmf.repository.servers.MemcachedServerRep;

import java.io.Serializable;

@Service
public class MemcachedServerService extends BaseCrudService<MemcachedServerDTO, MemcachedServerEntity> implements IMemcachedServerService {

  @Autowired
  private MemcachedServerRep rep;


  @Override
  protected MemcachedServerEntity constructEntity() {
    return new MemcachedServerEntity();
  }

  @Override
  protected MemcachedServerDTO constructDto() {
    return new MemcachedServerDTO();
  }

  @Override
  protected JpaRepository<MemcachedServerEntity, Serializable> getRepository() {
    return rep;
  }
}
