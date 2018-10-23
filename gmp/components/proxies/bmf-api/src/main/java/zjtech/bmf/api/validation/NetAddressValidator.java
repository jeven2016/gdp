package zjtech.bmf.api.validation;

import zjtech.common.util.net.NetAddressValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否合法的地址
 */
public class NetAddressValidator implements ConstraintValidator<ValidateNetAddress, String> {
    private ValidateNetAddress.Type[] validationTypes = {};

    @Override
    public void initialize(ValidateNetAddress constraintAnnotation) {
        validationTypes = constraintAnnotation.range();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        //只判断值不为空的情况下是否是有效的地址
        for (ValidateNetAddress.Type type : validationTypes) {
            switch (type) {
                case ALL:
                    return isValid(value);
                case IPV4:
                    return isValidIpv4(value);
                case IPV6:
                    return isValidIpv6(value);
                case FQDN:
                    return isValidFQDN(value);
                case HOSTNAME:
                    return isValidHostname(value);
            }
        }
        return true;
    }

    private static boolean isValidIpv4(String ipAddr) {
        return NetAddressValidatorUtil.isValidIpv4(ipAddr);
    }

    private static boolean isValidIpv6(String ipAddr) {
        return NetAddressValidatorUtil.isValidIpv6(ipAddr);
    }

    private static boolean isValidFQDN(String ipAddr) {
        return NetAddressValidatorUtil.isValidIpv4(ipAddr);
    }

    private static boolean isValidHostname(String hostname) {
        return NetAddressValidatorUtil.isValidHostname(hostname);
    }

    private static boolean isValid(String address) {
        return isValidIpv4(address) || isValidIpv6(address)
                || isValidFQDN(address) || isValidHostname(address);
    }


}
