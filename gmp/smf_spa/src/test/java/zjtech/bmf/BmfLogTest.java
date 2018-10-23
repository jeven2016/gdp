/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zjtech.smf.common.log.SmfLogTest;

/**
 * Created by zjtech on 16-7-4.
 */
public class BmfLogTest {

    private Logger logger = LoggerFactory.getLogger(BmfLogTest.class);

    @Test
    public void printLog() {
        for (int i = 0; i < 1000; i++) {
            logger.warn("hello world");
            logger.info("info.info");
        }
    }

}
