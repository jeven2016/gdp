package zjtech.bmf.api.exception;

import zjtech.bmf.api.result.BmfErrorCode;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;

public class BmfException extends RuntimeException implements IBmfException {
  private Integer errorCode;

  public BmfException(String message) {
    super(message);
    errorCode = BmfErrorCode.INTERNAL_EXCEPTION;
  }

  public BmfException(Exception e) {
    super(e);
    errorCode = BmfErrorCode.INTERNAL_EXCEPTION;
  }

  public BmfException(int errorCode, Exception e) {
    super(e);
    this.errorCode = errorCode;
  }

  public BmfException(int errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public IBmfResult getResult() {
    return new BmfResult();
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public Throwable fillInStackTrace() {
    return this;
  }
}
