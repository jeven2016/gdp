/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

import zjtech.smf.common.exception.SmfException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 错误信息对象
 */
public class ErrorMsg {

  /*资源对应的Key*/
  private String key;

  /*国际化资源*/
  private String message;

  /*异常信息*/
  private String throwable;

  /*该错误信息的级别*/
  private MessageLevel messageLevel = MessageLevel.Error;

  public ErrorMsg() {

  }

  public ErrorMsg(String key, String message) {
    this.key = key;
    this.message = message;
  }

  public ErrorMsg(String key, String message, MessageLevel messageLevel) {
    this.key = key;
    this.message = message;
    this.messageLevel = messageLevel;
  }

  public ErrorMsg(String key, String message, Throwable throwable) {
    this.key = key;
    this.message = message;
    transfer(throwable);
  }

  public ErrorMsg(String key, String message, MessageLevel messageLevel, Throwable throwable) {
    this.key = key;
    this.message = message;
    this.messageLevel = messageLevel;
    transfer(throwable);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    transfer(throwable);
  }

  public MessageLevel getMessageLevel() {
    return messageLevel;
  }

  public void setMessageLevel(MessageLevel messageLevel) {
    this.messageLevel = messageLevel;
  }

  private void transfer(Throwable throwable) {
    if (throwable == null) {
      this.throwable = null;
      return;
    }
    ByteArrayOutputStream bos = null;
    try {
      bos = new ByteArrayOutputStream();
      throwable.printStackTrace(new PrintStream(bos));
    } finally {
      if (bos != null) {
        this.throwable = bos.toString();
        try {
          bos.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

  }
}
