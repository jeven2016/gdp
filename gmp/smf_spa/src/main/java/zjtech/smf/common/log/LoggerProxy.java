/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;


/**
 * SLF4J Log proxy
 */
public class LoggerProxy implements Logger {

    //slf4j logger
    private Logger logger;

    //the FQCN
    private String loggerClassName;

    public LoggerProxy(Class<?> cls) {
        logger = LoggerFactory.getLogger(cls);
        this.loggerClassName = cls.getName();
    }

    public LoggerProxy(Logger logger) {
        this.logger = logger;
        this.loggerClassName = logger.getClass().getName();
    }

    private void log(Marker marker, int levelInt, String message, Object[] argArray, Throwable t) {
        LocationAwareLogger locationAwareLogger = ((LocationAwareLogger) logger);
        locationAwareLogger.log(marker, this.loggerClassName, levelInt, message, argArray, t);
    }

    //====================================================================================================
    // Trace log is not supported.
    @Override
    public void trace(Marker marker, String message, Throwable throwable) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(Marker marker, String message, Object... params) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(Marker marker, String message, Object param, Object param1) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(Marker marker, String message, Object param) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(Marker marker, String message) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(String message, Throwable throwable) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(String message, Object... parmas) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(String message, Object param, Object param1) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(String message, Object param) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public void trace(String message) {
        throw new RuntimeException("the trace log is not supported.");
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    //====================================================================================================
    @Override
    public void debug(Marker marker, String message, Throwable throwable) {
        log(marker, LocationAwareLogger.DEBUG_INT, message, null, throwable);
    }

    @Override
    public void debug(Marker marker, String message, Object... params) {
        log(marker, LocationAwareLogger.DEBUG_INT, message, params, null);
    }

    @Override
    public void debug(Marker marker, String message, Object param, Object param1) {
        log(marker, LocationAwareLogger.DEBUG_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void debug(Marker marker, String message, Object param) {
        log(marker, LocationAwareLogger.DEBUG_INT, message, new Object[]{param}, null);
    }

    @Override
    public void debug(Marker marker, String message) {
        log(marker, LocationAwareLogger.DEBUG_INT, message, null, null);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        log(null, LocationAwareLogger.DEBUG_INT, message, null, throwable);
    }

    @Override
    public void debug(String message, Object... parmas) {
        log(null, LocationAwareLogger.DEBUG_INT, message, parmas, null);
    }

    @Override
    public void debug(String message, Object param, Object param1) {
        log(null, LocationAwareLogger.DEBUG_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void debug(String message, Object param) {
        log(null, LocationAwareLogger.DEBUG_INT, message, new Object[]{param}, null);
    }

    @Override
    public void debug(String message) {
        log(null, LocationAwareLogger.DEBUG_INT, message, null, null);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    //====================================================================================================
    @Override
    public void info(Marker marker, String message, Throwable throwable) {
        log(marker, LocationAwareLogger.INFO_INT, message, null, throwable);
    }

    @Override
    public void info(Marker marker, String message, Object... params) {
        log(marker, LocationAwareLogger.INFO_INT, message, params, null);
    }

    @Override
    public void info(Marker marker, String message, Object param, Object param1) {
        log(marker, LocationAwareLogger.INFO_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void info(Marker marker, String message, Object param) {
        log(marker, LocationAwareLogger.INFO_INT, message, new Object[]{param}, null);
    }

    @Override
    public void info(Marker marker, String message) {
        log(marker, LocationAwareLogger.INFO_INT, message, null, null);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(String message, Throwable throwable) {
        log(null, LocationAwareLogger.INFO_INT, message, null, throwable);
    }

    @Override
    public void info(String message, Object... parmas) {
        log(null, LocationAwareLogger.INFO_INT, message, parmas, null);
    }

    @Override
    public void info(String message, Object param, Object param1) {
        log(null, LocationAwareLogger.INFO_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void info(String message, Object param) {
        log(null, LocationAwareLogger.INFO_INT, message, new Object[]{param}, null);
    }

    @Override
    public void info(String message) {
        log(null, LocationAwareLogger.INFO_INT, message, null, null);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    //====================================================================================================
    @Override
    public void warn(Marker marker, String message, Throwable throwable) {
        log(marker, LocationAwareLogger.WARN_INT, message, null, throwable);
    }

    @Override
    public void warn(Marker marker, String message, Object... params) {
        log(marker, LocationAwareLogger.WARN_INT, message, params, null);
    }

    @Override
    public void warn(Marker marker, String message, Object param, Object param1) {
        log(marker, LocationAwareLogger.WARN_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void warn(Marker marker, String message, Object param) {
        log(marker, LocationAwareLogger.WARN_INT, message, new Object[]{param}, null);
    }

    @Override
    public void warn(Marker marker, String message) {
        log(marker, LocationAwareLogger.WARN_INT, message, null, null);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        log(null, LocationAwareLogger.WARN_INT, message, null, throwable);
    }

    @Override
    public void warn(String message, Object... parmas) {
        log(null, LocationAwareLogger.WARN_INT, message, parmas, null);
    }

    @Override
    public void warn(String message, Object param, Object param1) {
        log(null, LocationAwareLogger.WARN_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void warn(String message, Object param) {
        log(null, LocationAwareLogger.WARN_INT, message, new Object[]{param}, null);
    }

    @Override
    public void warn(String message) {
        log(null, LocationAwareLogger.WARN_INT, message, null, null);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    //====================================================================================================
    @Override
    public void error(Marker marker, String message, Throwable throwable) {
        log(marker, LocationAwareLogger.ERROR_INT, message, null, throwable);
    }

    @Override
    public void error(Marker marker, String message, Object... params) {
        log(marker, LocationAwareLogger.ERROR_INT, message, params, null);
    }

    @Override
    public void error(Marker marker, String message, Object param, Object param1) {
        log(marker, LocationAwareLogger.ERROR_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void error(Marker marker, String message, Object param) {
        log(marker, LocationAwareLogger.ERROR_INT, message, new Object[]{param}, null);
    }

    @Override
    public void error(Marker marker, String message) {
        log(marker, LocationAwareLogger.ERROR_INT, message, null, null);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(String message, Throwable throwable) {
        log(null, LocationAwareLogger.ERROR_INT, message, null, throwable);
    }

    @Override
    public void error(String message, Object... parmas) {
        log(null, LocationAwareLogger.ERROR_INT, message, parmas, null);
    }

    @Override
    public void error(String message, Object param, Object param1) {
        log(null, LocationAwareLogger.ERROR_INT, message, new Object[]{param, param1}, null);
    }

    @Override
    public void error(String message, Object param) {
        log(null, LocationAwareLogger.ERROR_INT, message, new Object[]{param}, null);
    }

    @Override
    public void error(String message) {
        log(null, LocationAwareLogger.ERROR_INT, message, null, null);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
}
