/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.constants;

/**
 * 与用户试图相关的变量
 */
public interface ViewNameConstants {

  //login view
  String LOGIN_VIEW = "hello";

  //成功登录后的view *
  String MAIN_PAGE = "main";

  //memcached 服务器 list view
  String SERVERS_MEMCACHED_list = "servers/memcached_main";

  //memcached 集群list view
  String CLUSTERS_MEMCACHED_list = "servers/memcached_clusters";

  //memcached advance view
  String ADVANCE_MEMCACHED_list = "servers/memcached_advance";

  String INDEX_HTML = "index.html";
}
