import caches.interfaces.IMemcachedConfig;
import caches.memcached.MemcachedConfigVO;
import caches.memcached.MemCachedProxy;
import com.whalin.MemCached.SockIOPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class MemCachedProxyTest {
    MemCachedProxy proxy;

    @Before
    public void init() {
        proxy = MemCachedProxy.getInstance();
    }

    @After
    public void clear() {
        try {
            proxy.close();
        } catch (Exception e) {
//      System.out.println(e.getMessage());
        }
    }

    @Test
    public void testServers() {
        String[] servers = new String[]{"127.0.0.1:11211", "172.16.72.145:11211"};
        Integer[] weights = new Integer[]{1, 2};

        IMemcachedConfig memcachedConfig = new MemcachedConfigVO();
        memcachedConfig.setServers(servers);
        memcachedConfig.setWeights(weights);

        proxy.config(memcachedConfig, false);

        Assert.assertArrayEquals(proxy.getSockIOPool().getServers(), servers);
        Assert.assertArrayEquals(proxy.getSockIOPool().getWeights(), weights);
    }

    @Test
    public void testParams() {
        IMemcachedConfig memcachedConfig = new MemcachedConfigVO();
        memcachedConfig.setInitConn(2);
        memcachedConfig.setSocketTO(23);

        memcachedConfig.setInstanceName("kk");
        memcachedConfig.setMaintSleep(20);
        memcachedConfig.setMaxConn(555);
        memcachedConfig.setMaxIdle(66);
        memcachedConfig.setMinConn(4);
        memcachedConfig.setNagle(true);

        proxy.config(memcachedConfig, false);
        SockIOPool sockIOPool = proxy.getSockIOPool();

        Assert.assertArrayEquals(sockIOPool.getServers(), memcachedConfig.getServers());


        Assert.assertEquals(sockIOPool.getInitConn(), memcachedConfig.getInitConn());
        Assert.assertEquals(sockIOPool.getMinConn(), memcachedConfig.getMinConn());
        Assert.assertEquals(sockIOPool.getMaxConn(), memcachedConfig.getMaxConn());

        Assert.assertEquals(sockIOPool.getMaxIdle(), memcachedConfig.getMaxIdle());
        Assert.assertEquals(sockIOPool.getSocketTO(), memcachedConfig.getSocketTO());
        Assert.assertEquals(sockIOPool.getMaintSleep(), memcachedConfig.getMaintSleep());

        Assert.assertEquals(sockIOPool.getNagle(), memcachedConfig.isNagle());
        Assert.assertEquals(sockIOPool.getAliveCheck(), true);
        Assert.assertEquals(sockIOPool.getFailover(), true);
    }

    @Test
    public void testSetAndGet() {
        MemcachedConfigVO vo = new MemcachedConfigVO();
        String[] servers = new String[]{"127.0.0.1:11211"};
        Integer[] weights = new Integer[]{1};
        vo.setServers(servers);
        vo.setWeights(weights);
        proxy.config(vo);

        Integer val = 77244;
        proxy.set("test", val, new Date(60000));
        Assert.assertEquals(77244, ((Integer) proxy.get("test")).intValue());

        boolean result = proxy.set("dto", vo, new Date(60000));
        Assert.assertTrue(result);
        //the object should implement serializable interface
        vo = (MemcachedConfigVO) proxy.get("dto");
        Assert.assertTrue(vo.getServers().length == 1);
    }
}
