package zjtech.bmf.inject.zjtech.install.util;


import java.sql.Connection;

public interface IDbEvent {

  void beginToInit(Connection connection);
}
