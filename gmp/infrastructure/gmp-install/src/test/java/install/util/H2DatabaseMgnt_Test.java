package install.util;


import zjtech.bmf.inject.zjtech.install.util.H2DatabaseMgnt;
import zjtech.bmf.inject.zjtech.install.util.IDbMgnt;
import org.junit.Test;

public class H2DatabaseMgnt_Test {

  @Test
  public void testInit() {
    IDbMgnt dbMgnt = new H2DatabaseMgnt();
    dbMgnt.init();

  }
}
