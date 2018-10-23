package zjtech.dmf.domain.servers;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "memcached_cluster_server", schema = "", catalog = "smf_admin")
public class MemcachedClusterServerEntity extends BaseEntity {
  private int clusterid;
  private int serverid;


  @Basic
  @Column(name = "clusterid")
  public int getClusterid() {
    return clusterid;
  }

  public void setClusterid(int clusterid) {
    this.clusterid = clusterid;
  }

  @Basic
  @Column(name = "serverid")
  public int getServerid() {
    return serverid;
  }

  public void setServerid(int serverid) {
    this.serverid = serverid;
  }
}
