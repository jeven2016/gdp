package zjtech.common.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间日期工具类
 */
public class DateTimeUtlil {

  public static Date getCurrentDate() {
    return Calendar.getInstance(TimeZone.getDefault(), Locale.CHINESE).getTime();
  }
}
