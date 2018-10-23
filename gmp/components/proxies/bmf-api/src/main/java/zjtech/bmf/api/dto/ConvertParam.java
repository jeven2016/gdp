package zjtech.bmf.api.dto;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConvertParam {

  /**
   * 当属性为Collection时，标记集合内部存放的对象类型
   *
   * @return 对象类型
   */
  Class<?> innerBeanClass();

  /**
   * 源对象对当前属性的Set方法名
   *
   * @return 源对象的Set方法名
   */
  String sourceBeanSetter();
}
