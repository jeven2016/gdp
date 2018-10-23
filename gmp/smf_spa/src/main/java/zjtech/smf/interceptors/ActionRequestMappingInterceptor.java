/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import zjtech.smf.common.constants.IdentifierConstants;
import zjtech.smf.common.result.ISmfResult;
import zjtech.smf.common.result.ResultBuilder;

/**
 * 当Action中的方法抛出异常时，进行拦截病封装最终的返回结果对象
 */
@Aspect
@Component
public class ActionRequestMappingInterceptor {
  @Autowired
  @Qualifier(IdentifierConstants.ResultBuilder)
  private ResultBuilder resultBuilder;

  /**
   * Around增强
   * 过滤zjtech.smf.modules包下Action类的public方法，且该方法使用了@RequestMapping注解并且返回ISmfResult对象。
   * 如果方法中抛出了异常则封装一个默认失败的结果返回到client端，避免直接抛出异常。
   */
  @Around("execution(public zjtech.smf.common.result.ISmfResult zjtech.smf.modules..*.*(..)) " +
          "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public Object doAuditLog(ProceedingJoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();

    //获取对应的参数病打印到出错日志中去
    try {
      return joinPoint.proceed(args);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      ISmfResult smfResult = resultBuilder.build(false);
      smfResult.addGlobalError("globalError", "a exception encountered", throwable);
      return smfResult;
    }
  }
}
