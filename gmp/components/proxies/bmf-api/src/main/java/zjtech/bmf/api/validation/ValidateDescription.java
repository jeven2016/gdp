package zjtech.bmf.api.validation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import zjtech.bmf.api.constant.ValueConstants;
import zjtech.bmf.api.result.BmfErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验名称
 */
@Length(max = ValueConstants.MAX_LENGTH_OF_DESC,
        message = BmfErrorCode.Parameter.INVALID_LENGTH + "")
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {}) //这个不能缺
@Documented
public @interface ValidateDescription {
    String message() default BmfErrorCode.Parameter.INVALID_LENGTH + "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
