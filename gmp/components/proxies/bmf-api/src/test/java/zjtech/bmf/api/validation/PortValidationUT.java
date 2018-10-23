package zjtech.bmf.api.validation;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * 校验端口
 */
public class PortValidationUT {

  @Test
  public void testPort() {
    Port port = new Port();
    port.setPort(0);
    Set<ConstraintViolation<Port>> setResult = ValidationUtil.assertResult(port, set -> set.size() > 0);
    for (ConstraintViolation<Port> cst : setResult) {
      System.out.println(cst.getMessage());
      System.out.println(cst.getRootBeanClass().toString());
      System.out.println(cst.getPropertyPath().toString());
    }

  }


}

class Port {
  @ValidatePort
  private int port;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}

