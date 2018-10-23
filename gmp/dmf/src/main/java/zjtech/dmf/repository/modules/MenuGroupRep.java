package zjtech.dmf.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import zjtech.dmf.domain.modules.MenuGroupEntity;


public interface MenuGroupRep extends JpaRepository<MenuGroupEntity, Long> {
}
