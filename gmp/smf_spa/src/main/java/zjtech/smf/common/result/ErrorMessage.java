/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

/**
 * 错误信息对象
 */
public class ErrorMessage {

  /*资源对应的Key*/
  private String key;

  /*国际化资源*/
  private String message;

  /*该错误信息的级别*/
  private MessageLevel messageLevel = MessageLevel.Error;

  public ErrorMessage() {

  }

  public ErrorMessage(String key, String message) {
    this.key = key;
    this.message = message;
  }

  public ErrorMessage(String key, String message, MessageLevel messageLevel) {
    this.key = key;
    this.message = message;
    this.messageLevel = messageLevel;
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

}
