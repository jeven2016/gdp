package zjtech.bmf.inject.zjtech.install.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDbMgnt implements IDbMgnt,IDbEvent  {
  @Override
  public String getUserName() {
    return "dbAdmin";
  }

  /**
   * 当数据库不存在时，进行初始化
   *
   * @throws InstallUtilException
   */
  @Override
  public void init() throws InstallUtilException {
    Connection conn = null;
    try {
      Class.forName(getDriver());
      conn = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
      beginToInit(conn);
    } catch (Exception e) {
      throw new InstallUtilException(e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          throw new InstallUtilException(e);
        }
      }
    }
  }

  @Override
  public void start() throws InstallUtilException {

  }
}
