package zjtech.dmf.repository.servers;


import org.springframework.data.jpa.repository.JpaRepository;
import zjtech.dmf.domain.modules.ModuleEntity;
import zjtech.dmf.domain.servers.MemcachedServerEntity;

import java.io.Serializable;

/**
 * spring aop中，如果是实现的接口的，一定是用jdkProxy，如果一个类没有实现接口，才会用CGLIBProxy
 */
public interface MemcachedServerRep extends JpaRepository<MemcachedServerEntity, Serializable> {
}
