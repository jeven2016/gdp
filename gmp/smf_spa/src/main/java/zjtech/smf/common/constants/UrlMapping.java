package zjtech.smf.common.constants;


/**
 * URL Mapping for version 1.0
 */
public interface UrlMapping {
  String VERSION = "/v1";

  /**
   * Global
   */
  String INIT_SPA_USER_INIT = VERSION + "/init"; //初始化登录
  String USER_LOGIN = VERSION + "/login"; //登录
  String USER_WEB_SETTING = VERSION + "/settings";//当前用户的全局web 设置


  /**
   * 服务器管理
   */
  String MEMCACHED_SERVER = VERSION + "/memcached/server";
  String MEMCACHED_CLUSTER = VERSION + "/memcached/cluster";


  /**
   * 对应普通资源的CRUD请求路径
   */
  String GENERAL_CRUD_URI = VERSION + "/crud";


  /**
   * Common CRUD uri
   */
  String INIT_ONE_OBJECT = "/new";
  /**
   * 用户
   */


  String USER_LOGOUT = VERSION + "/logout"; //退出登录
  String SWITCH_LANGUAGE = "/language/{language}";//切换语言


}
