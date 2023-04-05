package com.nagp.log4j;

import org.apache.logging.log4j.Logger;

/**
 * ExtentManager class helps to achieve thread safety for the {@link
 * org.apache.logging.log4j.Logger} instance.
 */

public final class Log4jLogManager {

  /**
   * Private constructor to avoid external instantiation
   */
  private Log4jLogManager() {
  }

  private static ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();

  /**
   * Return thread safe {@link org.apache.logging.log4j.Logger} instance fetched from ThreadLocal
   * variable
   *
   * @return Thread safe Logger instance
   */
  public static Logger getLogger() {
    return loggerThreadLocal.get();
  }

  /**
   * Set the {@link org.apache.logging.log4j.Logger} instance to thread local variable
   *
   * @param logger Logger instance that needs to be saved from thread safety issue
   */
  public static void setLogger(Logger logger) {
    loggerThreadLocal.set(logger);
  }

}
