package zjtech.dmf.domain.servers;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "memcached_cluster", schema = "", catalog = "smf_admin")
public class MemcachedClusterEntity extends BaseEntity {
  private String name;
  private boolean disabled;
  private String description;

//  private List<MemcachedServerEntity> servers = new ArrayList<>();

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  /*@OneToMany(
          cascade = {CascadeType.ALL},
          fetch = FetchType.EAGER,
          targetEntity = MemcachedServerEntity.class,
          orphanRemoval = true */

  /**
   * 孤子删除，会将数据库中存在但Set集合中不存在的行删除掉，缺点是额外会多一条查询语句
   **//*
  )
  @JoinColumn(name = "person_id", nullable = false)*/
//  public List<MemcachedServerEntity> getServers() {
//    return servers;
//  }

//  public void setServers(List<MemcachedServerEntity> servers) {
//    this.servers = servers;
//  }
}
