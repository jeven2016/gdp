package zjtech.bmf.inject.zjtech.install.util;

public class InstallUtilException extends RuntimeException {
  public InstallUtilException(String msg) {
    super(msg);
  }

  public InstallUtilException(String msg, Throwable throwable) {
    super(msg, throwable);
  }

  public InstallUtilException(Throwable throwable) {
    super(throwable);
  }
}
