/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.result;

import java.util.HashMap;

/**
 * SMF的结果类接口
 */
public interface ISmfResult<E> {
    ISmfResult<E> addFieldErrorByKey(String field, String message);

    boolean isOk();

    ISmfResult<E> setOk(boolean ok);

    boolean isValidSession();

    ISmfResult<E> setValidSession(boolean validSession);

    E getAttachedObj();

    ISmfResult<E> setAttachedObj(E attachedObj);

    HashMap getErrorMsg();

    ISmfResult<E> setErrorMsg(HashMap errorMsg);

    ISmfResult<E> addGlobalError(String field, String message);

    ISmfResult<E> addGlobalError(String field, String message, Throwable throwable);
}
