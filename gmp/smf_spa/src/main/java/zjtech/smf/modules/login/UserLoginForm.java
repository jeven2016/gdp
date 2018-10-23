package zjtech.smf.modules.login;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import zjtech.smf.modules.global.form.BaseForm;

public class UserLoginForm extends BaseForm {

  @NotEmpty(message = "{login.username.empty}")
  @Length(min = 1, max = 20, message = "{login.username.length}")
  private String userName;

  @Length(min = 1, max = 20, message = "{login.password.length}")
  private String password;

  private String verifyCode;

  private String lan;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String name) {
    this.userName = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getLan() {
    return lan;
  }

  public void setLan(String lan) {
    this.lan = lan;
  }
}
