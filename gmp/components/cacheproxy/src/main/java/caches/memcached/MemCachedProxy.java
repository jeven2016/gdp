package caches.memcached;

import caches.interfaces.ICacheProxy;
import caches.interfaces.ICachedConfig;
import caches.interfaces.IMemcachedConfig;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class MemCachedProxy implements ICacheProxy {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static final MemCachedProxy proxy = new MemCachedProxy();
    private String instanceName = "default";

    private SockIOPool sockIOPool;
    private MemCachedClient memcachedClient;

    private long lastConfigTime;

    private ReentrantLock lock = new ReentrantLock();
    private ConcurrentHashMap<String, SockIOPool> sockPoolsMap = new ConcurrentHashMap<>();

    private MemCachedProxy() {

    }

    public static MemCachedProxy getInstance() {
        return proxy.setMemcachedClient();
    }

    public static MemCachedProxy getInstance(MemCachedClient memcachedClient) {
        return proxy.setMemcachedClient(memcachedClient);
    }

    public static MemCachedProxy getInstance(String instanceName) {
        return proxy.setMemcachedClient(instanceName);
    }

    public MemCachedProxy setMemcachedClient() {
        return setMemcachedClient(instanceName);
    }

    public MemCachedProxy setMemcachedClient(String instName) {
        if (this.memcachedClient == null) {
            this.memcachedClient = new MemCachedClient(instName);
        }
        return this;
    }

    public MemCachedProxy setMemcachedClient(MemCachedClient client) {
        if (this.memcachedClient == null) {
            this.memcachedClient = client;
        }
        return this;
    }

    public void updateInstance(String instName) {
        this.memcachedClient = new MemCachedClient(instName);
    }


    public void config(ICachedConfig cachedConfig, boolean startImmediately) {
        try {
            lock.lock();
            long currentTime = System.currentTimeMillis();

            //如果一秒内有多次config请求，则只配置第一次，其他的配置请求将被忽略
            if (lastConfigTime > 0 && currentTime - lastConfigTime < 1000) {
                logger.warning("Another configuration appears less than 1 second " +
                        "from last operation, so this will be ignored.");
            }

            if (sockIOPool == null) {
                init(cachedConfig, startImmediately);
            } else {
                sockIOPool.shutDown();
                init(cachedConfig, startImmediately);
            }
            lastConfigTime = currentTime;
        } finally {
            lock.unlock();
        }
    }

    public void config(ICachedConfig cachedConfig) {
        config(cachedConfig, true);
    }

    private void init(ICachedConfig cachedConfig, boolean startImmediately) {
        if (!(cachedConfig instanceof IMemcachedConfig)) {
            throw new RuntimeException("the config object is not a instance object of IMemcachedConfig");
        }

        //初始化SockIPPoll
        IMemcachedConfig memcachedConfig = (IMemcachedConfig) cachedConfig;
        if (memcachedConfig.getInstanceName() != null && !memcachedConfig.getInstanceName().trim().equals("")) {
            instanceName = memcachedConfig.getInstanceName();
        }
        sockIOPool = SockIOPool.getInstance(instanceName);

        //servers
        sockIOPool.setServers(memcachedConfig.getServers());
        sockIOPool.setWeights(memcachedConfig.getWeights());

        sockIOPool.setInitConn(memcachedConfig.getInitConn());
        sockIOPool.setMinConn(memcachedConfig.getMinConn());
        sockIOPool.setMaxConn(memcachedConfig.getMaxConn());

        sockIOPool.setMaxIdle(memcachedConfig.getMaxIdle());
        sockIOPool.setSocketTO(memcachedConfig.getSocketTO());
        sockIOPool.setMaintSleep(memcachedConfig.getMaintSleep());

        sockIOPool.setNagle(memcachedConfig.isNagle());
        sockIOPool.setAliveCheck(true);
        sockIOPool.setFailover(true);

        //start sock pool now
        if (startImmediately) {
            sockIOPool.initialize();
        }

        sockPoolsMap.putIfAbsent(instanceName, sockIOPool);
    }

    public void update(IMemcachedConfig memcachedConfig) {
        this.config(memcachedConfig);
    }

    @Override
    public boolean containsKey(String key) {
        return memcachedClient.keyExists(key);
    }

    @Override
    public Object get(String key) {
        return memcachedClient.get(key);
    }

    @Override
    public Map get(String... keys) {
        return memcachedClient.getMulti(keys);
    }

    @Override
    public boolean set(String key, Object value) {
        return memcachedClient.set(key, value);
    }

    @Override
    public boolean set(String key, Object value, Date expiry) {
        return memcachedClient.set(key, value, expiry);
    }

    @Override
    public boolean set(String key, Object value, long deadtime) {
        return memcachedClient.set(key, value, new Date(deadtime));
    }

    @Override
    public boolean delete(String key) {
        return memcachedClient.delete(key);
    }


    public SockIOPool getSockIOPool() {
        return sockIOPool;
    }


    public void close() {
        sockIOPool.shutDown();
    }

}
