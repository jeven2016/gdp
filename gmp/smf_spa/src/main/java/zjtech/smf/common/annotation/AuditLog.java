package zjtech.smf.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 允许对用户进行操作日志的记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {
  /**
   * 国际化资源的key
   */
  String key();

  /**
   * 操作成功对应的错误码
   */
  String successErrorCode();
}
