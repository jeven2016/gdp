/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

/**
 * 消息级别
 */
public enum MessageLevel {
  Info(0), /*提示级别*/
  Warning(1),/*警告级别*/
  Error(2); /*错误级别*/

  private int level;

  private MessageLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }
}
