/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.constants;

import java.util.Locale;

/**
 * 语言类型枚举类
 */
public enum LanguageEnum {
    /**
     * 中文
     */
    Chinese {
        public Locale getLocaleInfo() {
            return Locale.SIMPLIFIED_CHINESE;
        }
    },

    //英语
    English {
        public Locale getLocaleInfo() {
            return Locale.US;
        }
    };

    public static boolean acceptable(String value) {
        LanguageEnum[] values = LanguageEnum.values();
        for (LanguageEnum item : values) {
            if (item.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取国际化Locale信息
     *
     * @return
     */
    public Locale getLocaleInfo() {
        return LanguageEnum.Chinese.getLocaleInfo();
    }

    /**
     * 根据Locale获取语言枚举对象
     *
     * @param locale Locale
     * @return LanguageEnum
     */
    public static LanguageEnum getLanguageByLocale(Locale locale) {
        LanguageEnum[] values = LanguageEnum.values();
        for (LanguageEnum item : values) {
            if (item.getLocaleInfo().equals(locale)) {
                return item;
            }
        }
        return null;
    }
}
