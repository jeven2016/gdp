package zjtech.dmf.domain.modules;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "menu_item", schema = "", catalog = "smf_admin")
public class MenuItemEntity extends BaseEntity {
  private String name;
  private String link;
  private boolean disabled;
  private String description;
  private Date createTime;
  private Date lastUpdateTime;
  private MenuGroupEntity menuGroup;

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

  @ManyToOne(targetEntity = MenuGroupEntity.class, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "group_id", nullable = true)
  public MenuGroupEntity getMenuGroup() {
    return menuGroup;
  }

  public void setMenuGroup(MenuGroupEntity menuGroup) {
    this.menuGroup = menuGroup;
  }
}
