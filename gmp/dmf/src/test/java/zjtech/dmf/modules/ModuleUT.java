package zjtech.dmf.modules;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import zjtech.dmf.adminuser.AdminUserUT;
import zjtech.dmf.domain.modules.MenuGroupEntity;
import zjtech.dmf.domain.modules.MenuItemEntity;
import zjtech.dmf.domain.modules.ModuleEntity;
import zjtech.dmf.repository.modules.MenuGroupRep;
import zjtech.dmf.repository.modules.MenuItemRep;
import zjtech.dmf.repository.modules.ModulesRep;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ModuleUT extends AdminUserUT {

  @Autowired
  private ModulesRep modulesRep;

  @Autowired
  private MenuGroupRep menuGroupRep;

  @Autowired
  private MenuItemRep menuItemRep;

  @Test
  public void testGetModule() {
    List<ModuleEntity> list = modulesRep.findAll();
    System.out.println(list.size());
    list.forEach(entity -> {
      System.out.println(entity.getMenugroups().size());
    });
  }

  @Test
  public void testAddModule() {
    addModule();
  }

  @Test
  public void testDeleModule_byid() {
    Page<ModuleEntity> page = modulesRep.findAll(new PageRequest(0, 1));
    if (!page.hasNext()) {
      return;
    }
    ModuleEntity entitiy = (ModuleEntity) (page.iterator().next());
    modulesRep.delete(entitiy);
  }

  @Test
  public void testDeleteModule() {
    modulesRep.deleteAllInBatch();
    menuGroupRep.deleteAllInBatch();
    menuItemRep.deleteAllInBatch();
  }

  @Transactional
  private void addModule() {
    ModuleEntity moduleEntity = new ModuleEntity();
    moduleEntity.setName("后台管理");
    moduleEntity.setDescription("desc");
    moduleEntity.setDisabled(false);
    moduleEntity.setLink("www.ch.com");
    moduleEntity.setCreateTime(new Date());

    List<MenuGroupEntity> menuGroupEntities = generateMenuGroups(0, 1);
    List<MenuItemEntity> items = generateMenuItems(0, 1);

    MenuGroupEntity parent = new MenuGroupEntity();
    parent.setName("parent");
    parent.setLink("link");
    parent.setCreateTime(new Date());

    menuGroupEntities.forEach(menuGroupEntity -> {
      menuGroupEntity.setMenuItems(items);
      menuGroupEntity.setParentMenuGroup(parent);
    });

    moduleEntity.setMenugroups(menuGroupEntities);
    moduleEntity.getMenugroups().add(parent);
    modulesRep.save(moduleEntity);
  }

  private List<MenuGroupEntity> generateMenuGroups(int nameIndex, int count) {
    List<MenuGroupEntity> set = new ArrayList<>();
    MenuGroupEntity entity;
    for (int i = 0; i < count; i++) {
      entity = new MenuGroupEntity();
      entity.setName("group" + nameIndex + "_" + i);
      entity.setLink("link");
      entity.setDisabled(false);
      entity.setCreateTime(new Date());
      set.add(entity);
    }
    return set;
  }

  private List<MenuItemEntity> generateMenuItems(int nameIndex, int count) {
    List<MenuItemEntity> set = new ArrayList<>();
    MenuItemEntity entity;
    for (int i = 0; i < count; i++) {
      entity = new MenuItemEntity();
      entity.setName("item" + nameIndex + "_" + i);
      entity.setLink("link");
      entity.setDisabled(false);
      entity.setCreateTime(new Date());
      set.add(entity);
    }
    return set;
  }
}
