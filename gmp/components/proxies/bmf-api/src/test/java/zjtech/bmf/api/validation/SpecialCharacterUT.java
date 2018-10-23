package zjtech.bmf.api.validation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import zjtech.bmf.api.constant.CommonConst;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 测试特殊字符的校验
 */
public class SpecialCharacterUT {
    private static Validator validator;
    private String specialChars = CommonConst.SPECIAL_CHARS;

    @BeforeClass
    public static void prepare() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testContext() {
        City city = new City();
        city.setName("sd^jfljw_中国");
        Set<ConstraintViolation<City>> set = validator.validate(city);
        set.forEach(cv -> {
            System.out.println(cv.getMessage());
        });
    }

    @Test
    public void testSpecialChars() {
        City city = new City();

        city.setName("sdjfljw_sldw22333QWER");
        assertResult(city, (set) -> set.size() == 0);

        city.setName("sdjfljw_中国");
        assertResult(city, (set) -> set.size() == 0);

        city.setName("sd^jfljw_中国");
        assertResult(city, (set) -> set.size() > 0);

        city.setName("sd$jfljw_中国");
        assertResult(city, (set) -> set.size() > 0);
    }

    @Test
    public void testExcludedCharacters() {
        //test excluded string
        City2 city2 = new City2();

        city2.setName("sdfsdf$___i_(");
        assertResult2(city2, (set) -> set.size() == 0);

        city2.setName("sdfsdf?");
        assertResult2(city2, (set) -> set.size() > 0);
    }

    @Test
    public void testExtraCharacters() {
        //test excluded string
        City3 city3 = new City3();

        city3.setName("sdfsdf");
        assertResult2(city3, (set) -> set.size() == 0);

        city3.setName("sdfsdfB");
        assertResult2(city3, (set) -> set.size() == 0);

        city3.setName("sdfsdfabc");
        assertResult2(city3, (set) -> set.size() > 0);
    }

    private void assertResult(City city, Predicate<Set<ConstraintViolation<City>>> predicate) {
        long timer1 = System.currentTimeMillis();
        Set<ConstraintViolation<City>> set = validator.validate(city);
        long timer2 = System.currentTimeMillis();
        System.out.println((timer2 - timer1) + " ms.");
        Assert.assertTrue(predicate.test(set));
    }

    private void assertResult2(City2 city2, Predicate<Set<ConstraintViolation<City2>>> predicate) {
        long timer1 = System.currentTimeMillis();
        Set<ConstraintViolation<City2>> set = validator.validate(city2);
        long timer2 = System.currentTimeMillis();
        System.out.println((timer2 - timer1) + " ms.");
        Assert.assertTrue(predicate.test(set));
    }


    private void assertResult2(City3 city3, Predicate<Set<ConstraintViolation<City3>>> predicate) {
        long timer1 = System.currentTimeMillis();
        Set<ConstraintViolation<City3>> set = validator.validate(city3);
        long timer2 = System.currentTimeMillis();
        System.out.println((timer2 - timer1) + " ms.");
        Assert.assertTrue(predicate.test(set));
    }

}

class City {
    @ValidateSpecialCharacterForContext(message = "has special character")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class City2 {
    @ValidateSpecialCharacter(message = "has special character", excludeCharacters = "$(")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class City3 {
    @ValidateSpecialCharacter(message = "has special character", extraCharacters = "abc")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

