package zjtech.bmf.api.dto.modules;

import zjtech.common.result.BaseDTO;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


public class MenuGroupDTO extends BaseDTO {
  private String name;
  private String link;
  private boolean disabled;
  private String description;
  private Date createTime;
  private Date lastUpdateTime;
  private ModuleDTO module;
  private MenuGroupDTO parentMenuGroup;
  private Set<MenuGroupDTO> childMenuGroups = new LinkedHashSet<>();
  private Set<MenuItemDTO> menuItems = new LinkedHashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public ModuleDTO getModule() {
    return module;
  }

  public void setModule(ModuleDTO module) {
    this.module = module;
  }

  public MenuGroupDTO getParentMenuGroup() {
    return parentMenuGroup;
  }

  public void setParentMenuGroup(MenuGroupDTO parentMenuGroup) {
    this.parentMenuGroup = parentMenuGroup;
  }

  public Set<MenuGroupDTO> getChildMenuGroups() {
    return childMenuGroups;
  }

  public void setChildMenuGroups(Set<MenuGroupDTO> childMenuGroups) {
    this.childMenuGroups = childMenuGroups;
  }

  public Set<MenuItemDTO> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(Set<MenuItemDTO> menuItems) {
    this.menuItems = menuItems;
  }
}
