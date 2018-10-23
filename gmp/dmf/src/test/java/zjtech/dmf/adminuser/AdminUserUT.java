package zjtech.dmf.adminuser;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import zjtech.common.util.SecurityUtil;
import zjtech.dmf.domain.adminuser.UserEntity;
import zjtech.dmf.repository.user.UserRep;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true) //aop:aspectj-autoproxy 配置
@ContextConfiguration(locations = {"classpath:/spring/sp-dmf.xml"})
//@ContextConfiguration(classes = {DmfConfig.class})
public class AdminUserUT {

  @Resource
  UserRep userRep;

  @Resource
  JpaRepository jpa;

  @Test
  public void testUsers() {
    List<UserEntity> users = userRep.findAll();
    System.out.println("users=" + users.size());
  }

  @Test
  public void creatUser() {
    UserEntity entity = new UserEntity();
    entity.setDescription("hello");
    entity.setLastLogin(new Date());
    entity.setName("wzj2");
    entity.setPassword("hello");
    userRep.save(entity);
  }

  @Test(expected = Exception.class)
  public void updateLastLoginTime() {
    //模拟: Service方法中出现异常，update操作异常回滚。
    throwExceptionDuringUpdateLoginTime();
  }

  @Transactional
  private void throwExceptionDuringUpdateLoginTime() {
    userRep.updateLastLoginTime((long)1, new Date());
    throw new RuntimeException("test");
  }

  @Test
  public void testPwdMd5() {
    String pwd = "root";
    byte[] pwdBytes = SecurityUtil.encryptMD5(pwd.getBytes());
    String pwdString = SecurityUtil.bytes2HexString(pwdBytes);

    byte[] pwdBytes2 = SecurityUtil.encryptMD5(pwd.getBytes());
    String pwdString2 = SecurityUtil.bytes2HexString(pwdBytes);
    Assert.assertEquals(pwdString, pwdString2);
    System.out.println(pwdString + ",length=" + pwdString.length());

    pwd="$Wzjdsksdfjlsdjfrootddddddddddddddddddddddddddddddddddddddddddddddd";
    byte[] pwdBytes3 = SecurityUtil.encryptMD5(pwd.getBytes());
    String pwdString3 = SecurityUtil.bytes2HexString(pwdBytes3);
    System.out.println(pwdString3 + ",length=" + pwdString3.length());
  }
}
