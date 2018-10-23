/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.validation;

import zjtech.bmf.api.result.BmfErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 检验值是否不为空，如果值为null或空字符串则视为空值
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankValueValidator.class)
@Documented
public @interface NotBlankValue {
  String message() default BmfErrorCode.notBlank;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
