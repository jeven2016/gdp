package zjtech.smf.common.constants;

/**
 * 请求类型常量定义
 */
public enum OperationEnum {
  Get("get"),
  Add("add"),
  Update("update"),
  Delete("delete"),
  List("list");

  private String operation;

  private OperationEnum(String operation) {
    this.operation = operation;
  }

  public String getValue() {
    return operation;
  }
}
