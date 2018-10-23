package zjtech.bmf.api.validation;

import zjtech.bmf.api.constant.CommonConst;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验值中是否有特殊字符
 */
public class SpecialCharacterValidator implements ConstraintValidator<ValidateSpecialCharacter, String> {
  private String excludeString;
  private String customCharacters;
  private String extraCharacters;
  private Pattern pattern;
  private String reg = CommonConst.SPECIAL_CHARS;

  @Override
  public void initialize(ValidateSpecialCharacter constraintAnnotation) {
    excludeString = constraintAnnotation.excludeCharacters();
    customCharacters = constraintAnnotation.customCharacters();
    extraCharacters = constraintAnnotation.extraCharacters();

    //excludeString 和 extraCharacters 只能二选一
    if (!customCharacters.isEmpty()) {
      //需要使用自定义的特殊字符数组进行检测
      reg = customCharacters;
    } else {
      String specialChars = reg;

      //如果既要检测缺省的特殊字符，又要检测额外提供的特殊字符
      if (!extraCharacters.isEmpty()) {
        specialChars = specialChars.concat(extraCharacters);
        reg = specialChars;
      }

      //剔除不需要检测的特殊字符
      if (!excludeString.isEmpty()) {
        StringBuilder sb = new StringBuilder();
        int len = specialChars.length();
        char chr;
        for (int i = 0; i < len; i++) {
          chr = specialChars.charAt(i);
          if (excludeString.indexOf(chr) > -1) {
            continue;
          }
          sb.append(chr);
        }
        reg = sb.toString();
      }

    }

    if (!reg.isEmpty()) {
      reg = "[" + reg + "]";
    }
    pattern = Pattern.compile(reg);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (pattern == null || value == null || value.isEmpty()) {
      return true;
    }
    Matcher matcher = pattern.matcher(value);
    if (matcher.find()) {
      return false;
    }
    return true;
  }
}
