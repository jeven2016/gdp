package zjtech.smf.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取受spring管理的对象
 */
@Component
@Lazy(value = false)  //非延迟加载
public class GlobalBeanFactory implements ApplicationContextAware {
  private static ApplicationContext context;

  private static WeakHashMap<String, Class> classesMap = new WeakHashMap<>();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  /**
   * 获取BeanFactory
   *
   * @return ApplicationContext
   */
  public static ApplicationContext getAppContext() {
    return context;
  }

  /**
   * 获取对象实例
   *
   * @param cls 对象Class
   * @return 对象实例
   */
  public static <T> T getBean(Class<T> cls) {
    return (T) context.getBean(cls);
  }

  /**
   * 根据类的class（以字符串）获取对象实例
   *
   * @param classPath Class的完整路径
   * @param isClass   classPath是否是类的完整路径
   * @return 对象实例
   * @throws ClassNotFoundException
   */
  public static <T> T getBean(String classPath, boolean isClass) throws ClassNotFoundException {
    if (!isClass) {
      throw new IllegalArgumentException("The isClass should be specified to ture in order to retrieve bean by its class");
    }
    Class<T> cls = classesMap.get(classPath);
    if (cls == null) {
      cls = (Class<T>) Class.forName(classPath);
    }
    return context.getBean(cls);
  }

  /**
   * 获取对象实例
   *
   * @param name 实例名称
   * @return 对象实例
   */
  public static <T> T getBean(String name) {
    return (T) context.getBean(name);
  }


}
