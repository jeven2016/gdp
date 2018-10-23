package zjtech.bmf.api.dto.modules;

import zjtech.common.result.BaseDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ModuleDTO extends BaseDTO {
  private String name;
  private String link;
  private boolean disabled;
  private String description;
  private Date createTime;
  private Date lastUpdateTime;
  private Set<MenuGroupDTO> menugroups = new HashSet<>();

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

  public Set<MenuGroupDTO> getMenugroups() {
    return menugroups;
  }

  public void setMenugroups(Set<MenuGroupDTO> menugroups) {
    this.menugroups = menugroups;
  }
}
