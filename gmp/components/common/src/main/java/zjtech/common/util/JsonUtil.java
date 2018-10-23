/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * Json工具类
 */
public class JsonUtil {
    private static XStream jsonXstream = new XStream(new JettisonMappedXmlDriver());

    /**
     * 获取对象的JSON字符串
     *
     * @param json JSON Object
     * @return JSON字符串
     */
    public static <JSON> String toJsonString(JSON json) {
        return jsonXstream.toXML(json);
    }
}
