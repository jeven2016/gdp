/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.exception;

import zjtech.smf.common.result.ISmfResult;

/**
 * SMF Exception
 */
public class SmfException extends Exception {
  private ISmfResult result;

  public SmfException() {

  }

  public SmfException(ISmfResult result) {
    this.result = result;
  }

  public SmfException(String msg) {
    super(msg);
  }

  public SmfException(String msg, ISmfResult result) {
    super(msg);
    this.result = result;
  }

  public ISmfResult getResult() {
    return result;
  }

  public void setResult(ISmfResult result) {
    this.result = result;
  }

  /**
   * 覆盖此方法，
   * Throwable类中的方法会有性能消耗，开销在于:
   * 1. 是一个synchronized方法
   * 2. 需要填充线程运行堆栈信息
   *
   * @return Throwable
   */
  @Override
  public Throwable fillInStackTrace() {
    return this;
  }
}
