package zjtech.smf.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

@Aspect
@Component
public class AuditLogInterceptor {

  /**
   * Around增强,只对wzjtech.smf.action包下Action类的public方法，且该方法使用了@AuditLog注解，进行拦截。
   */
  @Around("execution(public * zjtech.smf.action..*.*(..)) && @annotation(zjtech.smf.common.annotation.AuditLog)")
  public Object doAuditLog(ProceedingJoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();

    BeanPropertyBindingResult bindingResult = null;
    for (Object arg : args) {
      System.out.print(arg + ",");
    }

    try {
      return joinPoint.proceed(args);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return "exception from AuditLogInterceptor";
    }

  }
}
