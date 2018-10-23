package zjtech.bmf.api.validation;

import zjtech.bmf.api.result.BmfErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验网络地址，包括IPv4,IPv6, FQDN 域名，主机名
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NetAddressValidator.class)
@Documented
public @interface ValidateNetAddress {
  String message() default BmfErrorCode.invalidAddress;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 校验范围。当不指定时，缺省判断若其中任意一个条件符合则算校验通过（IPv4,IPv6, FQDN 域名，主机名）
   */
  Type[] range() default {Type.ALL};

  enum Type {
    IPV4,
    IPV6,
    FQDN,
    HOSTNAME,
    ALL
  }
}
