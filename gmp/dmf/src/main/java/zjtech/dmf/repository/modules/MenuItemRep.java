package zjtech.dmf.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import zjtech.dmf.domain.modules.MenuItemEntity;

public interface MenuItemRep extends JpaRepository<MenuItemEntity, Long> {
}
