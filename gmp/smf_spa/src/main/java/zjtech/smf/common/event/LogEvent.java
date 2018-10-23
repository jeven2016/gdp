package zjtech.smf.common.event;

/**
 * Created by root on 14-12-25.
 */
public class LogEvent implements IEvent {
  private LogLevel level;
  private String method;
  private String message;
  private Object[] paramValues;
  private Exception exception;

  public LogLevel getLevel() {
    return level;
  }

  public void setLevel(LogLevel level) {
    this.level = level;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object[] getParamValues() {
    return paramValues;
  }

  public void setParamValues(Object[] paramValues) {
    this.paramValues = paramValues;
  }

  public Exception getException() {
    return exception;
  }

  public void setException(Exception exception) {
    this.exception = exception;
  }
}
