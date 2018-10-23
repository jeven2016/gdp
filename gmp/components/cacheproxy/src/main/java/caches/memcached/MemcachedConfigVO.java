package caches.memcached;

import caches.interfaces.IMemcachedConfig;

import java.util.List;

/**
 * Memecached 服务器配置对象
 */
public class MemcachedConfigVO implements IMemcachedConfig {

  // 创建一个实例对象SocketIOPool
  private String instanceName = "default";

  // 设置Memcached Server
  String[] servers;

  // 设置各个Memcached server的权重，list长度与servers长度相同
  Integer[] weights;

  private int initConn = 10;

  private int minConn = 2;

  // 最大250个连接
  private int maxConn = 1000;

  // 一个连接最大句柄时间为6小时
  private int maxIdle = 1000 * 60 * 60 * 6;

  // 设置休眠以维持线程，它每30秒苏醒以此维护池大小
  private int maintSleep = 30;

  //关闭套接字的缓存，当准备好了数据包后就进行发送；
  private boolean nagle;

  // 连接建立后对超时的控制
  private int socketTO = 3000;

  // 开启压缩功能
  private boolean compressEnable = true;

  // 大于64K开始压缩
  private int compressThreshold = 64 * 1024;


  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public String[] getServers() {
    return servers;
  }

  public void setServers(String[] servers) {
    this.servers = servers;
  }

  public Integer[] getWeights() {
    return weights;
  }

  public void setWeights(Integer[] weights) {
    this.weights = weights;
  }

  public int getInitConn() {
    return initConn;
  }

  public void setInitConn(int initConn) {
    this.initConn = initConn;
  }

  public int getMinConn() {
    return minConn;
  }

  public void setMinConn(int minConn) {
    this.minConn = minConn;
  }

  public int getMaxConn() {
    return maxConn;
  }

  public void setMaxConn(int maxConn) {
    this.maxConn = maxConn;
  }

  public int getMaxIdle() {
    return maxIdle;
  }

  public void setMaxIdle(int maxIdle) {
    this.maxIdle = maxIdle;
  }

  public int getMaintSleep() {
    return maintSleep;
  }

  public void setMaintSleep(int maintSleep) {
    this.maintSleep = maintSleep;
  }

  public boolean isNagle() {
    return nagle;
  }

  public void setNagle(boolean nagle) {
    this.nagle = nagle;
  }

  public int getSocketTO() {
    return socketTO;
  }

  public void setSocketTO(int socketTO) {
    this.socketTO = socketTO;
  }

  public boolean isCompressEnable() {
    return compressEnable;
  }

  public void setCompressEnable(boolean compressEnable) {
    this.compressEnable = compressEnable;
  }

  public int getCompressThreshold() {
    return compressThreshold;
  }

  public void setCompressThreshold(int compressThreshold) {
    this.compressThreshold = compressThreshold;
  }
}
