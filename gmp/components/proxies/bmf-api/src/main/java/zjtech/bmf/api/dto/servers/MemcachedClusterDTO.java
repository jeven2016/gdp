package zjtech.bmf.api.dto.servers;

import zjtech.bmf.api.dto.ConvertParam;
import zjtech.common.result.BaseDTO;

import java.util.ArrayList;
import java.util.List;


public class MemcachedClusterDTO extends BaseDTO {
  private String name;
  private boolean disabled;
  private String description;

  @ConvertParam(innerBeanClass = MemcachedServerDTO.class, sourceBeanSetter = "setServers")
  private List<MemcachedServerDTO> servers = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getDisabled() {
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

  public List<MemcachedServerDTO> getServers() {
    return servers;
  }

  public void setServers(List<MemcachedServerDTO> servers) {
    this.servers = servers;
  }
}
