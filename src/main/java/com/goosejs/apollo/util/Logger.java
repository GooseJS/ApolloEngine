package com.goosejs.apollo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;

public class Logger
{

    public static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private static boolean debug;

    private Logger() {} // No constructor, shouldn't have instances

    public static boolean isDebugEnabled()
    {
        return debug;
    }

    public static void setDebug(boolean value)
    {
        debug = value;
    }

    public static void info(Object s)
    {
        LOGGER.info(s);
    }

    public static void info(Object s, Throwable t)
    {
        LOGGER.info(s, t);
    }

    public static void info(Marker m, String s, Object... o)
    {
        LOGGER.info(m, s, o);
    }

    public static void warn(Object s)
    {
        LOGGER.warn(s);
    }

    public static void warn(Object s, Throwable t)
    {
        LOGGER.warn(s, t);
    }

    public static void warn(String s, Object p, Throwable t)
    {
        LOGGER.warn(s, p, t);
    }

    public static void error(Object s)
    {
        LOGGER.error(s);
    }

    public static void error(Throwable t)
    {
        LOGGER.error(t);
    }

    public static void error(Object s, Throwable t)
    {
        LOGGER.error(s, t);
    }

    public static void fatal(Object s)
    {
        LOGGER.fatal(s);
    }

    public static void fatal(Object s, Throwable t)
    {
        LOGGER.fatal(s, t);
    }

    public static void debug(Object s)
    {
        if (debug)
            LOGGER.info("[DEBUG] " + s);
    }

    public static void debug(Throwable t)
    {
        if (debug)
            LOGGER.info("[DEBUG] ", t);
    }

    public static void debug(Object s, Throwable t)
    {
        if (debug)
            LOGGER.info("[DEBUG] " + s, t);
    }

    public static void debug(String s, Object... o)
    {
        if (debug)
            LOGGER.info("[DEBUG] " + s, o);
    }

    public static void debug(Marker m, String s, Object... o)
    {
        if (debug)
        {
            LOGGER.info(m, "[DEBUG] " + s, o);
        }
    }

}
