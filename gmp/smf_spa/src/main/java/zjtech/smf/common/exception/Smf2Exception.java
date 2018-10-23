/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.exception;

import zjtech.smf.common.result.SmfResult;

public class Smf2Exception extends RuntimeException {
  private SmfResult result;

  public Smf2Exception() {

  }

  public Smf2Exception(SmfResult result) {
    this.result = result;
  }

  public Smf2Exception(String msg) {
    super(msg);
  }

  public Smf2Exception(String msg, SmfResult result) {
    super(msg);
    this.result = result;
  }

  public SmfResult getResult() {
    return result;
  }

  public void setResult(SmfResult result) {
    this.result = result;
  }
}
