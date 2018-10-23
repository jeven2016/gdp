package zjtech.smf.common.constants;


/**
 * 属性名称常量
 */
public interface AttributesConstants {
  /**
   * Application Context 范围的系统变量
   */
  String WEB_CONTEXT_PATH = "WEB_CONTEXT_PATH";
  String STATIC_RESOURCE_URL = "STATIC_RESOURCE_URL";
  String JSP_ROOT_PATH = "JSP_ROOT_PATH";
  String THEME_SETTING = "THEME_SETTING";
  String DEFAULT_THEME = "DEFAULT_THEME";
  String ENABLE_VERIFYCODE_FORADMINLOGIN = "enableVerifyCodeForAdminLogin"; //是否启用管理员
  /**
   * 默认的主题名称
   */

  /*通过Servlet加载的spring容器在ServletContext中关联的属性名称*/
  String WebServletContextName = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.smf";


  /**
   * Request范围的变量
   */
  String DEFAULT_LANGUAGE = "lan";
  String RESULT = "result";
  String CURRENT_OPER = "currentOper";//当前操作类型
  String LIST = "list";


  /**
   * Session范围的常量
   */
  String CURRENT_THEME = "CURRENT_THEME";
  String CURRENT_USER = "CURRENT_USER";

  /**
   * 其他业务模块使用的常量
   */
  interface UserInfo{
    String USER = "user";
  }

}
