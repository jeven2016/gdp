package zjtech.bmf.inject.zjtech.install.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * H2数据库工具类
 */
public class H2DatabaseMgnt extends BaseDbMgnt {
  @Override
  public String getUrl() {
    Path path = Paths.get("");
    Path filePath = path.resolve("conf/admin_db");
    return "jdbc:h2:" + filePath.toAbsolutePath().toString()+"?MODE=MySQL";
  }

  @Override
  public String getPassword() {
    return "dbAdmin";
  }

  @Override
  public String getDriver() {
    return "org.h2.Driver";
  }

  /**
   * 当初始化连接获得对应的Connection对象后，执行数据库的初始化
   *
   * @param connection
   */
  @Override
  public void beginToInit(Connection connection) {
    Path path = Paths.get("");
    System.out.println("path1="+ path.toAbsolutePath());
    path = path.resolve("conf/sqlfiles/h2/smf_admin.sql");
    try {
      System.out.println(path.toAbsolutePath());
      path = Paths.get("/Users/jujucom/Documents/develop/projects/gdp/conf/db/sqlfiles/h2/smf_admin.sql");
      Stream<String> stream = Files.lines(path, Charset.forName("utf-8"));
      StringBuilder sb = new StringBuilder();
      stream.forEach(line -> {
        sb.append(line);
      });
      PreparedStatement ps = connection.prepareStatement(sb.toString());
      ps.executeUpdate();
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  }
}
