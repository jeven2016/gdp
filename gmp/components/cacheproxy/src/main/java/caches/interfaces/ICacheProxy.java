package caches.interfaces;


import java.util.Date;
import java.util.Map;

public interface ICacheProxy<T> {

    /**
     * 判断是否存在对应的对象
     *
     * @param key 缓存的keyy
     * @param key 缓存的key
     * @return 缓存中的对象
     * @return是否存在对应的对象
     */
    boolean containsKey(String key);

    /**
     * 根据key获取缓存对象
     *
     * @param key 缓存的key
     * @return 缓存中的对象
     */
    T get(String key);

    /**
     * 根据key的集合，获取存放的对象Map
     *
     * @param keys 缓存的key
     * @return 缓存中的对象Map
     */
    Map<String, T> get(String... keys);

    /**
     * 缓存对象至缓存中
     *
     * @param key   缓存的key
     * @param value 缓存的对象
     */
    boolean set(String key, T value);

    /**
     * 缓存对象至缓存中,并指定失效时间
     *
     * @param key    缓存的key
     * @param value  缓存的对象
     * @param expiry 过期日期
     */
    boolean set(String key, Object value, Date expiry);

    /**
     * 缓存对象至缓存中,并指定失效时间（毫秒）
     *
     * @param key      缓存的key
     * @param value    缓存的对象
     * @param deadtime 失效时间（毫秒）
     */
    boolean set(String key, Object value, long deadtime);

    /**
     * 删除缓存对象
     *
     * @param key 需要删除缓存对象的key
     * @return 删除成功或失败
     */
    boolean delete(String key);

}
