package caches.interfaces;

/**
 * Memecached 服务器配置接口
 */
public interface IMemcachedConfig extends ICachedConfig {

  String getInstanceName();

  void setInstanceName(String instanceName);

  String[] getServers();

  void setServers(String[] servers);

  Integer[] getWeights();

  void setWeights(Integer[] weights);

  int getInitConn();

  void setInitConn(int initConn);

  int getMinConn();

  void setMinConn(int minConn);

  int getMaxConn();

  void setMaxConn(int maxConn);

  int getMaxIdle();

  void setMaxIdle(int maxIdle);

  int getMaintSleep();

  void setMaintSleep(int maintSleep);

  boolean isNagle();

  void setNagle(boolean nagle);

  int getSocketTO();

  void setSocketTO(int socketTO);

  boolean isCompressEnable();

  void setCompressEnable(boolean compressEnable);

  int getCompressThreshold();

  void setCompressThreshold(int compressThreshold);
}
