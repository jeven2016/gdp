package zjtech.smf.common.constants;

/**
 * 菜单的链接类型
 */
public enum LinkTypeEnum {
  internal_link("I_Link"),
  external_link("E_Link"),
  javascript_link("JS_Link");
  private String linkType;

  private LinkTypeEnum(String linkType) {
    this.linkType = linkType;
  }

  public String getValue() {
    return linkType;
  }
}
