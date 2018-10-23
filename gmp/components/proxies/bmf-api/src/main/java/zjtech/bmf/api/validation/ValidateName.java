package zjtech.bmf.api.validation;

import zjtech.bmf.api.constant.ValueConstants;
import zjtech.bmf.api.result.BmfErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static zjtech.bmf.api.result.ValidationResult.PREFIX;
import static zjtech.bmf.api.result.ValidationResult.SUFFIX;

/**
 * 校验名称(不能为空，字符长度在min和max之间，且不能包含特殊字符)
 */
@NotBlankValue
@ValidateSpecialCharacter
@Size(min = ValueConstants.MIN_LENGTH_OF_NAME, max = ValueConstants.MAX_LENGTH_OF_NAME,
        message = BmfErrorCode.invalidLength)
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateName {
  String message() default "Invalid Name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
