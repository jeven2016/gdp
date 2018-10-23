/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import zjtech.smf.common.SmfWebUtils;

import java.util.HashMap;

/**
 * SMF Result　Builder
 */
@Component("ResultBuilder")
public class ResultBuilder {

  public <E> ISmfResult<E> build() {
    ISmfResult<E> result = new ResponseResult<E>();
    return postBuild(result);
  }

  public <E> ISmfResult<E> build(boolean ok) {
    ISmfResult<E> result = new ResponseResult<E>(ok);
    return postBuild(result);
  }

  public <E> ISmfResult<E> build(HashMap<String, ?> attrsMap) {
    return build();
  }

  public <E> ISmfResult<E> build(BindingResult bindingResult) {
    ISmfResult<E> result = new ResponseResult<E>(bindingResult);
    return postBuild(result);
  }

  /**
   * Create a SMF Result object
   *
   * @param result      BindingResult
   * @param attachedObj Attachment
   * @return ISmfResult
   */
  public <E> ISmfResult<E> build(BindingResult result, E attachedObj) {
    return new ResponseResult<>(result, attachedObj);
  }

  protected <E> ISmfResult<E> postBuild(ISmfResult<E> result) {
    //设置用户是否已登录的信息
    boolean isUserLoggedIn = SmfWebUtils.isUserLoggedIn();
    result.setValidSession(isUserLoggedIn);
    return result;
  }
}
