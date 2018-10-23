package zjtech.smf.modules.serverMgnt;

import org.hibernate.validator.constraints.Length;
import zjtech.smf.common.constants.SmfValueConstants;
import zjtech.smf.modules.global.form.BaseForm;

public class MemcachedServerForm extends BaseForm {

  @Length(min = SmfValueConstants.MIN_LENGTH_OF_NAME, max = SmfValueConstants.MAX_LENGTH_OF_NAME,
          message = "{common.error.name.length}")
  private String name;

  private String address;

  private int port;


  private boolean disabled;

  private String description;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
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
}
