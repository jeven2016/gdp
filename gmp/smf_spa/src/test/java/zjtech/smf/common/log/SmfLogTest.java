/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.log;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class SmfLogTest {

    private Logger logger = LoggerFactory.getLogger(SmfLogTest.class);

    //@Before
    public void before() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        // Call context.reset() to clear any previous configuration, e.g. default
        // configuration. For multi-step configuration, omit calling context.reset().
        context.reset();
        InputStream inputStream = this.getClass().getResourceAsStream("/lb-for-reference.xml");

        //重新加载配置文件，并设置日志级别为Error
        try {
            configurator.doConfigure(inputStream);
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printLog() {
        for (int i = 0; i < 1000; i++) {
            logger.warn("hello world");
            logger.info("info.info");
        }
    }
}
