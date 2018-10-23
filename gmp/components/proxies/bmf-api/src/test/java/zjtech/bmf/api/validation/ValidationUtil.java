package zjtech.bmf.api.validation;

import org.junit.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.function.Predicate;

public class ValidationUtil {
  public static Validator getValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    return validator;
  }


  public static <T> Set<ConstraintViolation<T>> assertResult(T city, Predicate<Set<ConstraintViolation<T>>> predicate) {
    long timer1 = System.currentTimeMillis();
    Set<ConstraintViolation<T>> set = getValidator().validate(city);
    System.out.println("len="+set.size());
    long timer2 = System.currentTimeMillis();
//    System.out.println((timer2 - timer1) + " ms.");
    Assert.assertTrue(predicate.test(set));
    return set;
  }

}
