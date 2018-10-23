package zjtech.smf.common;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;
import zjtech.smf.common.constants.AttributesConstants;
import zjtech.smf.common.constants.LanguageEnum;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * 获取web相关对象的工具类
 */
public class SmfWebUtils {


  /**
   * 获取ServletContext
   *
   * @return ServletContext
   */
  public static ServletContext getServletContext() {
    return getRequest().getServletContext();
  }

  /**
   * 获取HttpSession
   *
   * @param allowNew 当Session不存在时，是否允许创建一个新的session
   * @return 获取HttpSession
   */
  public static HttpSession getSession(boolean allowNew) {

    return getRequest().getSession(allowNew);
  }

  /**
   * 从HttpSession中获取储存的对象
   *
   * @param attribute 属性
   * @return 存储在HttpSession中的对象
   */
  public static Object getValueFromSession(String attribute) {
    HttpServletRequest request = getRequest();
    return WebUtils.getSessionAttribute(request, attribute);
  }

  /**
   * Get HttpServletRequest
   *
   * @return HttpServletRequest
   */
  public static HttpServletRequest getRequest() {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return attr.getRequest();
  }


  /**
   * 获取当前国际化对应的Locale对象
   */
  public static Locale getCurrentLocale() {
    HttpServletRequest request = getRequest();

    Locale locale = (Locale) WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    if (locale == null) {
      //获取默认Locale
      locale = LanguageEnum.Chinese.getLocaleInfo();
    }
    return locale;
  }

  /**
   * 获取国际化资源
   *
   * @param messageKey 资源的key
   * @param values     资源中的赋值变量数组
   * @return 国际化资源
   */
  public static String getMessage(String messageKey, Object... values) {
    return getMessage(messageKey, SmfWebUtils.getCurrentLocale(), values);
  }

  /**
   * 获取国际化资源
   *
   * @param messageKey 资源的key
   * @param values     资源中的赋值变量数组
   * @return 国际化资源
   */
  public static String getMessage(String messageKey, Locale locale, Object... values) {
    MessageSource messageSource = GlobalBeanFactory.getBean(MessageSource.class);
    String message = null;
    try {
      message = messageSource.getMessage(messageKey, values, locale);
    } catch (NoSuchMessageException e) {
      e.printStackTrace();
      return messageSource.getMessage("error.message.lacked", new String[]{messageKey}, locale);
    }
    if (message != null) {
      message = getEscapedString(message);
    }
    return message;
  }

  /**
   * 获取经过HTML转义后的字符串
   *
   * @param value 字符串
   * @return 转义后的字符串
   */
  public static String getEscapedString(String value) {
    if (NumberUtils.isDigits(value)) {
      //是错误码
      return getMessage(value);
    } else {
      return value;
    }
//    return StringEscapeUtils.escapeHtml4(value);
  }

  /**
   * 从Session重获取Local对象
   */
  public static Locale getLocalFromSession() {

    HttpServletRequest request = getRequest();
    Locale locale = (Locale) WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    return locale;
  }

  /**
   * 通过Servlet，从ServletContext中获取Spring容器
   *
   * @param context ServletContext
   * @return WebApplicationContext
   */
  public static WebApplicationContext getWebAppCtxFromServletContext(ServletContext context) {
    WebApplicationContext webApplicationContext = (WebApplicationContext) context
            .getAttribute(AttributesConstants.WebServletContextName);
    return webApplicationContext;
  }


  /**
   * 通过全局配置加载，则通过此方式获取Spring容器
   *
   * @param context ServletContext
   * @return WebApplicationContext
   */
  public static WebApplicationContext getWebAppCtxFromGlobalContext(ServletContext context) {
    WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
    return webApplicationContext;
  }


  /**
   * 更新当前语言设置
   *
   * @param response HttpServletResponse
   * @param lan      language
   */
  public static void changeLanguage(HttpServletResponse response, String lan) {
    boolean isValidLanguage = !StringUtils.isEmpty(lan) && LanguageEnum.acceptable(lan);
    if (!isValidLanguage) {
      return;
    }
    HttpSession session = SmfWebUtils.getSession(false);

    if (session == null) {
      //根据语言信息切换语言(只在request范围生效)
      setLocaleForRequestScope(response);
    } else {
      Locale sessionLocale = SmfWebUtils.getLocalFromSession();
      LanguageEnum languageEnum = LanguageEnum.valueOf(lan);
      Locale newLocale = languageEnum.getLocaleInfo();

      //如果未设置语言或需要修改语言设置
      if (sessionLocale == null || !sessionLocale.equals(newLocale)) {
        //session中存储当前国际化设置
        WebUtils.setSessionAttribute(getRequest(), SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, newLocale);
      }
    }
  }

  /**
   * 根据语言信息切换语言(只在request范围生效)
   *
   * @param response HttpServletResponse
   */
  public static void setLocaleForRequestScope(HttpServletResponse response) {
    HttpServletRequest req = getRequest();

    //用户未登录时切换语言将设置wei范围的国际化信息
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(req);
    if (localeResolver != null) {
      Locale locale = getCurrentLocale();
      localeResolver.setLocale(req, response, locale);
    }
  }

  /**
   * 判断当前请求对应的用户是否已经登录
   *
   * @return 用户是否已经登录
   */
  public static boolean isUserLoggedIn() {
    HttpServletRequest request = getRequest();
    Object userObj = WebUtils.getSessionAttribute(request, AttributesConstants.CURRENT_USER);
    if (userObj == null) {
      return false;
    }
    return true;
  }


}
