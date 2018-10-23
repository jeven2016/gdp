/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.global.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.WebUtils;
import zjtech.bmf.api.dto.user.UserDTO;
import zjtech.bmf.api.exception.BmfException;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.smf.common.SmfWebUtils;
import zjtech.smf.common.constants.AttributesConstants;
import zjtech.smf.common.constants.IdentifierConstants;
import zjtech.smf.common.result.ISmfResult;
import zjtech.smf.common.result.ResultBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class BaseAction {
    private MessageSource messageSource;

    @Autowired
    @Qualifier(IdentifierConstants.ResultBuilder)
    private ResultBuilder resultBuilder;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 获取HttpSession
     *
     * @return 获取HttpSession
     */
    protected HttpSession getSession() {

        return SmfWebUtils.getSession(false);
    }

    /**
     * 获取HttpSession
     *
     * @return 获取HttpSession
     */
    protected HttpSession getSession(boolean allowNew) {

        return SmfWebUtils.getSession(allowNew);
    }

    /**
     * 从HttpSession中获取储存的对象
     *
     * @param attribute 属性
     */
    protected Object getFromSessionByAttr(String attribute) {
        return SmfWebUtils.getValueFromSession(attribute);
    }

    /**
     * 获取HttpRequest
     *
     * @return HttpRequest
     */
    protected HttpServletRequest getRequest() {
        return SmfWebUtils.getRequest();
    }

    /**
     * Create a SMF Result object
     *
     * @return ISmfResult
     */
    protected <E> ISmfResult<E> createResult() {
        return resultBuilder.build();
    }

    /**
     * Create a SMF Result object
     *
     * @param result BindingResult
     * @return ISmfResult
     */
    public <E> ISmfResult<E> createResult(BindingResult result) {
        return resultBuilder.build(result);
    }

    /**
     * Create a SMF Result object
     *
     * @param result      BindingResult
     * @param attachedObj Attachment
     * @return ISmfResult
     */
    public <E> ISmfResult<E> createResult(BindingResult result, E attachedObj) {
        return resultBuilder.build(result, attachedObj);
    }

    /**
     * 获取当前国际化对应的Locale对象
     */
    protected Locale getCurrentLocale() {
        return SmfWebUtils.getCurrentLocale();
    }

    /**
     * 从Session重获取Local对象
     */
    protected Locale getLocaleFromSession() {
        return SmfWebUtils.getLocalFromSession();
    }

    /**
     * 获取资源对象
     */
    protected String getMessage(String key, Object... values) {
        return messageSource.getMessage(key, values, getCurrentLocale());
    }

    /**
     * 获取当前Session中的用户
     *
     * @return 当前用户
     */
    protected UserDTO getCurrentUser() {
        return (UserDTO) getFromSessionByAttr(AttributesConstants.CURRENT_USER);
    }

    /**
     * 将BmfResult结果转换为SmfResult
     *
     * @param bmfResult BmfResult
     * @return SmfResult
     */
    protected <E> ISmfResult<E> retrieveSmfResult(IBmfResult<?> bmfResult) {
        return retrieveSmfResult(bmfResult, null);
    }

    /**
     * 将BmfResult结果转换为SmfResult
     *
     * @param bmfResult   BmfResult
     * @param smfResult   SmfResult
     * @param attachedObj attached object
     * @return SmfResult
     */
    protected <E> ISmfResult<E> retrieveSmfResult(IBmfResult<?> bmfResult, ISmfResult<E> smfResult, E attachedObj) {
        smfResult = retrieveSmfResult(bmfResult, smfResult);
        smfResult.setAttachedObj(attachedObj);
        return smfResult;
    }

    /**
     * 将BmfResult结果转换为SmfResult
     *
     * @param bmfResult BmfResult
     * @param smfResult SmfResult
     * @return SmfResult
     */
    protected <E> ISmfResult<E> retrieveSmfResult(IBmfResult<?> bmfResult, ISmfResult<E> smfResult) {
        if (smfResult == null) {
            smfResult = createResult();
        }

        smfResult.setOk(bmfResult.isOk());
        smfResult.setAttachedObj((E) bmfResult.getAttachment());
        if (!smfResult.isOk()) {
            smfResult.addGlobalError("globalError", bmfResult.getErrorCode().toString());
        }
        return smfResult;
    }

    /**
     * 将异常封装为SmfResult
     *
     * @param e Exception
     * @return SmfResult
     */
    protected <E> ISmfResult<E> retrieveSmfResult(Exception e) {
        ISmfResult<E> result = createResult();
        if (e != null) {
            result.setOk(false);
            if (e instanceof BmfException) {
                BmfException bmfException = (BmfException) e;
                result.addGlobalError("globalError", bmfException.getErrorCode().toString());
            } else {
                result.addGlobalError("globalError", "error.internal", e);
            }
        }
        return result;
    }
}
