/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 检验值是否不为空，如果值为null或空字符串则视为空值
 */
public class NotBlankValueValidator implements ConstraintValidator<NotBlankValue, String> {


  @Override
  public void initialize(NotBlankValue notBlankValue) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.trim().isEmpty()) {
      return false;
    }
    return true;
  }

}
