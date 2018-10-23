/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户端响应结果，用以取代旧的result实现。
 */
public class ResponseResult<E> implements ISmfResult<E> {
  //操作结果是否成功
  private boolean isOk = true;

  //用户是否已经登录并有生效的session存在
  private boolean validSession = false;

  // 关联的对象，用于结果输出显示
  private E attachedObj;

  //检测出的各类错误
  private HashMap<String, List<ErrorMsg>> errorMsg = new HashMap();
  public static final String FIELD_ERROR = "fieldErrors";
  public static final String GLOBAL_ERROR = "globalErrors";

  public ResponseResult() {
  }

  public ResponseResult(boolean isOk) {
    this.isOk = isOk;
  }


  public ResponseResult(BindingResult result) {
    //only to add field errors
    addFieldError(result);
  }

  public ResponseResult(BindingResult result, E attachedObj) {
    this(result);
    this.attachedObj = attachedObj;
  }

  /**
   * 从经过Spring Bean Validator校验结果中筛选出Field错误信息
   *
   * @param result BindingResult
   */
  public ResponseResult<E> addFieldError(BindingResult result) {
    if (result.hasFieldErrors()) {
      checkResult(false);
      checkErrorList(FIELD_ERROR);
      List<ErrorMsg> msgList = errorMsg.get(FIELD_ERROR);
      List<ErrorMsg> allFieldMsgsList = result.getFieldErrors().stream().map(fieldError
              -> new ErrorMsg(fieldError.getField(), fieldError.getDefaultMessage()))
              .collect(Collectors.toList());
      msgList.addAll(allFieldMsgsList);

    }
    return this;
  }


  /**
   * 添加字段属性的错误信息。
   *
   * @param field   字段属性名称
   * @param message error信息
   * @return ResponseResult
   */
  public ResponseResult<E> addFieldErrorByKey(String field, String message) {
    addError(FIELD_ERROR, field, message, null);
    return this;
  }

  /**
   * 添加全局操作结果的错误信息
   */
  public ResponseResult<E> addGlobalError(String field, String message) {
    addError(GLOBAL_ERROR, field, message, null);
    return this;
  }

  /**
   * 添加全局操作结果的错误信息并附带详细的异常信息
   */
  public ResponseResult<E> addGlobalError(String field, String message, Throwable throwable) {
    addError(GLOBAL_ERROR, field, message, throwable);
    return this;
  }

  /*
   * 向对应的错误队列中加入对应的错误信息
   */
  private void addError(String key, String field, String message, Throwable throwable) {
    checkResult(false);
    checkErrorList(key);
    List<ErrorMsg> msgList = errorMsg.get(key);
    msgList.add(new ErrorMsg(field, message, throwable));
  }

  private void checkResult(boolean finalResult) {
    if (this.isOk != finalResult) {
      this.isOk = finalResult;
    }
  }

  private void checkErrorList(String key) {
    if (!errorMsg.containsKey(key)) {
      errorMsg.put(key, new ArrayList<>());
    }
  }


  public boolean isOk() {
    return isOk;
  }

  public ResponseResult<E> setOk(boolean ok) {
    isOk = ok;
    return this;
  }

  public boolean isValidSession() {
    return validSession;
  }

  public ResponseResult<E> setValidSession(boolean validSession) {
    this.validSession = validSession;
    return this;
  }

  public E getAttachedObj() {
    return attachedObj;
  }

  public ResponseResult<E> setAttachedObj(E attachedObj) {
    this.attachedObj = attachedObj;
    return this;
  }

  public HashMap getErrorMsg() {
    return errorMsg;
  }

  public ResponseResult<E> setErrorMsg(HashMap errorMsg) {
    this.errorMsg = errorMsg;
    return this;
  }
}
