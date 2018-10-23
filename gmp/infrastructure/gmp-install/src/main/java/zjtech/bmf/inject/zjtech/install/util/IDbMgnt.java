package zjtech.bmf.inject.zjtech.install.util;

import java.sql.Connection;

/**
 * 数据库初始化
 */
public interface IDbMgnt {

  public String getUrl();

  public String getUserName();

  public String getPassword();

  public String getDriver();

  public void init() throws InstallUtilException;

  public void start() throws InstallUtilException;
}
