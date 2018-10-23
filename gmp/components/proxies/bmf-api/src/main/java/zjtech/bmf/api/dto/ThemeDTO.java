/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.dto;


import zjtech.common.result.BaseDTO;

import java.io.Serializable;

public class ThemeDTO<ID extends Serializable> extends BaseDTO {

  //名称
  private String name="";

  //如果name由一对花括号包裹，则表示使用的是资源文件中的配置从而不直接显示name的值
  private String displayName = "";

  //主题文件存放的连接或目录
  private String link = "";

  //是否启用该主题
  private boolean enabled = true;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
