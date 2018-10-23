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
 * 不允许存在特殊字符
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpecialCharacterValidatorForContext.class)
@Documented
public @interface ValidateSpecialCharacterForContext {
    String message() default BmfErrorCode.Parameter.SPECIAL_CHARACTER_EXIST + "";

    /**
     * 需要被排除的特殊字符，即不需要进行检测的字符
     */
    String excludeCharacters() default "";

    /**
     * 需要检测是否存在的特殊字符，如不设置则使用缺省的特殊字符数组去检测。
     * 如果设置了则不适用defalutSpecialCharsReg校验规则
     */
    String customCharacters() default "";

    /**
     * 其他需要跟着默认规则一起校验的特殊字符数组
     */
    String extraCharacters() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
