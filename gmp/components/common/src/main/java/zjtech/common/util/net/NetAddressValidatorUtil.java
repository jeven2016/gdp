package zjtech.common.util.net;


import org.apache.commons.validator.routines.InetAddressValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The net address validator
 */
public class NetAddressValidatorUtil {
    private static final Pattern fqdnPattern = Pattern.compile("(?=^.{1,254}$)(^(?:(?!\\d+\\.|-)[a-zA-Z0-9_\\-]{1,63}(?<!-)\\.?)+(?:[a-zA-Z]{2,})$)",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern hostnamePattern = Pattern.compile("^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$",
            Pattern.CASE_INSENSITIVE);

    private static InetAddressValidator validator = InetAddressValidator.getInstance();

    /**
     * Check Whether the ip address is a valid IPv4 or IPv6 address
     *
     * @param ipAddress ip address
     * @return boolean   Whether the ip address is a valid IPv4 or IPv6 address
     */
    public static boolean isValidIp(String ipAddress) {
        return validator.isValidInet4Address(ipAddress) || validator.isValidInet6Address(ipAddress);
    }

    /**
     * Check Whether the ip address is a valid IPv4 address
     *
     * @param ipAddress ip address
     * @return boolean  Whether the ip address is a valid IPv4 address
     */
    public static boolean isValidIpv4(String ipAddress) {
        return validator.isValidInet4Address(ipAddress);
    }

    /**
     * Check Whether the ip address is a valid IPv6 address
     *
     * @param ipAddress ip address
     * @return Whether the ip address is a valid IPv6 address
     */
    public static boolean isValidIpv6(String ipAddress) {
        return validator.isValidInet6Address(ipAddress);
    }

    /**
     * Check Whether the FQDN address is a valid FQDN address
     *
     * @param fqdnAddress FQDN address
     * @return Whether the FQDN address is a valid FQDN address
     */
    public static boolean isValidFQDN(String fqdnAddress) {
        try {
            Matcher matcher = fqdnPattern.matcher(fqdnAddress);
            return matcher.matches();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Check Whether the hostname is a valid hostname
     *
     * @param hostname hostname
     * @return Whether the hostname is a valid hostname
     */
    public static boolean isValidHostname(String hostname) {
        try {
            Matcher matcher = hostnamePattern.matcher(hostname);
            return matcher.matches();
        } catch (Exception e) {
        }
        return false;
    }
}