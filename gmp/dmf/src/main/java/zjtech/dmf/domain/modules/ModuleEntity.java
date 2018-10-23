package zjtech.dmf.domain.modules;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "module", schema = "", catalog = "smf_admin")
public class ModuleEntity extends BaseEntity {
  private String name;
  private String link;
  private boolean disabled;
  private String description;
  private Date createTime;
  private Date lastUpdateTime;
  private List<MenuGroupEntity> menugroups = new ArrayList<>();

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

  @OneToMany(
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
          fetch = FetchType.EAGER,
          targetEntity = MenuGroupEntity.class,
          orphanRemoval = true
  )
  @JoinColumn(name = "module_id", nullable = true)
  public List<MenuGroupEntity> getMenugroups() {
    return menugroups;
  }

  public void setMenugroups(List<MenuGroupEntity> menugroups) {
    this.menugroups = menugroups;
  }
}
