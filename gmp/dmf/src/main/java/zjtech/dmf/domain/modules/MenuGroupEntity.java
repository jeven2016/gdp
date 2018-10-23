package zjtech.dmf.domain.modules;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "menu_group", schema = "", catalog = "smf_admin")
public class MenuGroupEntity extends BaseEntity {
  private String name;
  private String link;
  private boolean disabled;
  private String description;
  private Date createTime;
  private Date lastUpdateTime;
  private ModuleEntity module;
  private MenuGroupEntity parentMenuGroup;
  private List<MenuGroupEntity> childMenuGroups = new ArrayList<>();
  private List<MenuItemEntity> menuItems = new ArrayList<>();

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "link")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Basic
  @Column(name = "disabled")
  public boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  @Basic
  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Basic
  @Column(name = "create_time")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Basic
  @Column(name = "last_update_time")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  @ManyToOne(targetEntity = ModuleEntity.class, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "module_id", nullable = true)
  public ModuleEntity getModule() {
    return module;
  }

  public void setModule(ModuleEntity moduleEntity) {
    this.module = moduleEntity;
  }

  @OneToMany(
          cascade = {CascadeType.ALL},
          fetch = FetchType.EAGER,
          targetEntity = MenuItemEntity.class,
          orphanRemoval = true
  )
  @JoinColumn(name = "group_id", nullable = true)
  public List<MenuItemEntity> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(List<MenuItemEntity> menuItems) {
    this.menuItems = menuItems;
  }

  @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE},
          targetEntity = MenuGroupEntity.class)
  @JoinColumn(name = "parent_id")
  public MenuGroupEntity getParentMenuGroup() {
    return parentMenuGroup;
  }

  public void setParentMenuGroup(MenuGroupEntity parentMenuGroup) {
    this.parentMenuGroup = parentMenuGroup;
  }

  @OneToMany(
          cascade = {CascadeType.ALL},
          fetch = FetchType.EAGER,
          targetEntity = MenuGroupEntity.class,
          orphanRemoval = true
  )
  @JoinColumn(name = "parent_id")
  public List<MenuGroupEntity> getChildMenuGroups() {
    return childMenuGroups;
  }

  public void setChildMenuGroups(List<MenuGroupEntity> childMenuGroups) {
    this.childMenuGroups = childMenuGroups;
  }
}
