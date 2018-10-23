package zjtech.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * 加密解密工具类
 */
public class SecurityUtil {
  private static byte[] HEXS =
          {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static final String KEY_SHA = "SHA";
  public static final String KEY_MD5 = "MD5";

  private static final String DEFAULT_MD5_SALT = "gdp_smf_salt";

  /**
   * MAC算法可选以下多种算法
   * <p>
   * <pre>
   * HmacMD5
   * HmacSHA1
   * HmacSHA256
   * HmacSHA384
   * HmacSHA512
   * </pre>
   */
  public static final String KEY_MAC = "HmacMD5";

  /**
   * BASE64解密
   *
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] decryptBASE64(String key) {
    try {
      return (new BASE64Decoder()).decodeBuffer(key);
    } catch (IOException e) {
      throw new IllegalArgumentException("faild to decode the key with code Base64");
    }
  }

  /**
   * BASE64加密
   *
   * @param key
   * @return
   * @throws Exception
   */
  public static String encryptBASE64(byte[] key) {
    return (new BASE64Encoder()).encodeBuffer(key);
  }

  /**
   * MD5
   *
   * @param data Data
   * @return 算法类型
   */
  public static byte[] encryptMD5(byte[] data) {
    return encrypt(data, KEY_MD5);
  }

  /**
   * 获得经过MD5加密后包含混淆码的字符串
   *
   * @return 经过MD5加密后的字符串
   */
  public static String getMD5String(String value) {
    Objects.requireNonNull(value);

    return getMD5String(value, DEFAULT_MD5_SALT);
  }

  /**
   * 获得经过MD5加密后的字符串
   *
   * @return 经过MD5加密后的字符串
   */
  public static String getMD5StringWithoutSalt(String value) {
    Objects.requireNonNull(value);

    return getMD5String(value, null);
  }

  /**
   * 获得经过MD5加密后的字符串，可以指定混淆码进行加密
   *
   * @return 经过MD5加密后的字符串
   */
  public static String getMD5String(String value, String salt) {
    Objects.requireNonNull(value);
    if (salt != null) {
      value += salt;
    }
    try {
      return bytes2HexString(encryptMD5(value.getBytes("UTF-8")));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encrypt(byte[] data, String type) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance(type);
      md.update(data);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("Failed to get digest instance of MD5");
    }

    return md.digest();
  }

  /**
   * SHA加密
   *
   * @param data
   * @return
   * @throws Exception
   */
  public static byte[] encryptSHA(byte[] data) {
    return encrypt(data, KEY_SHA);

  }

  /**
   * 初始化HMAC密钥
   *
   * @return
   * @throws Exception
   */
  public static String initMacKey() {
    KeyGenerator keyGenerator = null;
    try {
      keyGenerator = KeyGenerator.getInstance(KEY_MAC);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("faild to get digest instance of HmacMD5");
    }

    SecretKey secretKey = keyGenerator.generateKey();
    return encryptBASE64(secretKey.getEncoded());
  }

  /**
   * HMAC加密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] encryptHMAC(byte[] data, String key) {

    SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
    Mac mac = null;
    try {
      mac = Mac.getInstance(secretKey.getAlgorithm());
      mac.init(secretKey);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new IllegalArgumentException("failed to decode the key with code Base64");
    }

    return mac.doFinal(data);

  }

  /**
   * Byte to Hex string
   *
   * @param b data
   * @return String
   */
  public static String bytes2HexString(byte[] b) {
    byte[] buff = new byte[2 * b.length];
    for (int i = 0; i < b.length; i++) {
      buff[2 * i] = HEXS[(b[i] >> 4) & 0x0f];
      buff[2 * i + 1] = HEXS[b[i] & 0x0f];
    }
    return new String(buff);
  }
}
