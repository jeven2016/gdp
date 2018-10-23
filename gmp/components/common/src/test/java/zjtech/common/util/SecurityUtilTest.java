package zjtech.common.util;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class SecurityUtilTest {

  @Test
  public void testMD5() {
    String pd = SecurityUtil.getMD5String("admin");
    System.out.println(SecurityUtil.getMD5String("1"));

    String pd2 = SecurityUtil.getMD5String("admin");
    Assert.assertEquals(pd, pd2);

    String pd3 = SecurityUtil.getMD5String("admin3");
    Assert.assertNotSame(pd, pd3);
  }

  @Test(expected = Throwable.class)
  public void testNullValue(){
    SecurityUtil.getMD5String(null);
  }
}
