/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

import org.springframework.validation.BindingResult;
import org.springframework.web.util.WebUtils;
import zjtech.common.result.IResult;
import zjtech.smf.common.SmfWebUtils;
import zjtech.smf.common.constants.AttributesConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * SMF返回结果
 */
public class SmfResult<E> implements IResult {

  //操作结果是否成功
  private boolean isOk = true;

  //用户是否已经登录并有生效的session存在
  private boolean validSession = true;

  //全局操作结果的提示信息
  private List<ErrorMessage> globalErrors = new ArrayList<>();

  //Form中的属性配置错误信息
  private List<ErrorMessage> fieldErrors = new ArrayList<>();

  //提示信息
  private List<ErrorMessage> notices = new ArrayList<>();

  // 关联的对象，用于结果输出显示
  private E attachedObj;

  public SmfResult(BindingResult result) {
    //only to add field errors
    addFieldError(result);
  }

  public SmfResult() {
    updateValidSession();
  }

  public SmfResult(boolean isOk) {
    setOk(isOk);
    updateValidSession();
  }

  public SmfResult<E> updateValidSession() {
    HttpServletRequest request = SmfWebUtils.getRequest();
    Object userObj = WebUtils.getSessionAttribute(request, AttributesConstants.CURRENT_USER);
    if (userObj == null) {
      setValidSession(false);
    } else {
      setValidSession(true);
    }
    return this;
  }


  /**
   * 是否全局操作成功
   */
  public boolean isOk() {
    return isOk;
  }

  public SmfResult<E> setOk(boolean isOk) {
    this.isOk = isOk;
    return this;
  }

  /**
   * 是否有全局操作结果的信息
   */
  public boolean isGlobalErrorsEmpty() {
    return globalErrors.isEmpty();
  }

  /**
   * 添加全局操作结果的错误信息
   */
  public SmfResult<E> addNotice(String key, String message) {
    message = SmfWebUtils.getEscapedString(message);
    this.notices.add(new ErrorMessage(key, message));
    return this;
  }

  /**
   * 添加Global错误提示。
   *
   * @param key        字段属性名称
   * @param messageKey 资源的key
   * @param values
   * @return SmfResult
   */
  public SmfResult<E> addNoticeByMessageKey(String key, String messageKey, Object... values) {
    String message = SmfWebUtils.getMessage(messageKey, values);
    this.notices.add(new ErrorMessage(key, message));
    return this;
  }

  /**
   * 添加字段属性的错误提示。
   * 如果List中已经有对应名称的Field，则在该对象中继续添加。
   *
   * @param field   字段属性名称
   * @param message 国际化资源的信息
   * @return SmfResult
   */
  public SmfResult<E> addFieldError(String field, String message) {
    message = SmfWebUtils.getEscapedString(message);
    this.fieldErrors.add(new ErrorMessage(field, message));
    checkResut(false);
    return this;
  }

  /**
   * 添加字段属性的错误提示。
   *
   * @param field      字段属性名称
   * @param messageKey 资源的key
   * @param values
   * @return SmfResult
   */
  public SmfResult<E> addFieldErrorByMessageKey(String field, String messageKey, Object... values) {
    String message = SmfWebUtils.getMessage(messageKey, values);
    this.fieldErrors.add(new ErrorMessage(field, message));
    checkResut(false);
    return this;
  }

  private void checkResut(boolean finalResult) {
    if (this.isOk != finalResult) {
      this.isOk = finalResult;
    }
  }

  /**
   * 添加全局操作结果的错误信息
   */
  public SmfResult<E> addGlobalError(String key, String message) {
    message = SmfWebUtils.getEscapedString(message);
    this.globalErrors.add(new ErrorMessage(key, message));
    return this;
  }

  /**
   * 添加Global错误提示。
   *
   * @param key        字段属性名称
   * @param messageKey 资源的key
   * @param values
   * @return SmfResult
   */
  public SmfResult<E> addGlobalErrorByMessageKey(String key, String messageKey, Object... values) {
    String message = SmfWebUtils.getMessage(messageKey, values);
    this.globalErrors.add(new ErrorMessage(key, message));
    return this;
  }

  /**
   * 从经过Spring Bean Validator校验结果中筛选出Field错误信息
   *
   * @param result BindingResult
   */
  public SmfResult<E> addFieldError(BindingResult result) {
    if (result.hasFieldErrors()) {
      result.getFieldErrors().forEach(fieldError -> {
        addFieldError(fieldError.getField(), SmfWebUtils.getEscapedString(fieldError.getDefaultMessage()));
      });
      setOk(false);
    }
    return this;
  }

  public E getAttachedObj() {
    return attachedObj;
  }

  public SmfResult<E> setAttachedObj(E attachedObj) {
    this.attachedObj = attachedObj;
    return this;
  }

  /**
   * 是否有Form校验的错误信息
   */
  public boolean isFieldErrorsEmpty() {
    return fieldErrors.isEmpty();
  }

  public boolean isNoticesEmpty() {
    return notices.isEmpty();
  }

  public int getGlobalErrorsCount() {
    return globalErrors.size();
  }

  public int getFieldErrorsCount() {
    return fieldErrors.size();
  }

  public List<ErrorMessage> getGlobalErrors() {
    return globalErrors;
  }

  public List<ErrorMessage> getFieldErrors() {
    return fieldErrors;
  }

  public List<ErrorMessage> getNotices() {
    return notices;
  }

  public boolean isValidSession() {
    return validSession;
  }

  public SmfResult<E> setValidSession(boolean validSession) {
    validSession = validSession;
    return this;
  }
}
