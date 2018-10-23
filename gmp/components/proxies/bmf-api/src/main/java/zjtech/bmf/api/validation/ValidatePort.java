package zjtech.bmf.api.validation;

import zjtech.bmf.api.result.BmfErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * 校验端口
 */
@NotNull
@Min(value = 1000, message = BmfErrorCode.lessThanMinValue)
@Max(value = 65535, message = BmfErrorCode.greatThanMaxValue)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ValidatePort {
  String message() default BmfErrorCode.Parameter.INVALID_PORT + "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
