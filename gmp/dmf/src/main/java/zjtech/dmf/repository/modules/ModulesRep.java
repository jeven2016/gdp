package zjtech.dmf.repository.modules;


import org.springframework.data.jpa.repository.JpaRepository;
import zjtech.dmf.domain.modules.ModuleEntity;

public interface ModulesRep extends JpaRepository<ModuleEntity, Long> {
}
