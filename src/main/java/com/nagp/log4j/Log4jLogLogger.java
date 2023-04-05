package com.nagp.log4j;

import static com.nagp.constants.FrameworkConstant.getCurrentLogFolderPathWithTimeStamp;
import static com.nagp.constants.MessageConstants.TESTCASE_EXECUTION_END_MESSAGE;
import static com.nagp.constants.MessageConstants.TESTCASE_EXECUTION_START_MESSAGE;
import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static org.apache.logging.log4j.ThreadContext.put;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;

/**
 * This class is responsible for generating logs in File and Console with thread safety feature
 */
public final class Log4jLogLogger {

  private Log4jLogLogger() {
  }

  /**
   * Writes the log for informing about starting of the test case execution and calls the method
   * which generates file for logs
   *
   * @param testCaseName Name of the testcase that needs to printed on logs.
   */
  public static synchronized void startTestCase(String testCaseName) {
    startLog(testCaseName);
    info(format(TESTCASE_EXECUTION_START_MESSAGE, testCaseName));
  }

  /**
   * Writes the log for informing about ending of the test case execution.
   *
   * @param testCaseName Name of the testcase that needs to printed on logs.
   * @param status       status of testcase execution.
   */
  public static void endTestCase(String testCaseName, String status) {
    info(format(TESTCASE_EXECUTION_END_MESSAGE, testCaseName, status));
  }

  /**
   * Create the file with same name as testCaseName and replacing _ with space
   *
   * @param testCaseName name of the testCase
   */
  private static void startLog(String testCaseName) {
    put("logFilename", getCurrentLogFolderPathWithTimeStamp() + (testCaseName.replace(" ", "_")));
  }

  /**
   * Initialize the thread safe Logger
   */
  public static void intializeLogger() {
    if (Log4jLogManager.getLogger() == null) {
      Log4jLogManager.setLogger(LogManager.getLogger());
    }
  }

  /**
   * This method finds the name of class and method name for the testCase running to write on logs
   *
   * @return concatenated class and method name of the test currently running
   */
  public static String getCallInfo() {
    String callInfo;
    String className = currentThread().getStackTrace()[3].getClassName();
    String methodName = currentThread().getStackTrace()[3].getMethodName();
    callInfo = className + ":" + methodName + " --> ";
    return callInfo;
  }

  /**
   * Write error log on both console and file with given message and method and class name
   *
   * @param message message for error log
   */
  public static void error(Object message) {
    Log4jLogManager.getLogger().error(getCallInfo() + message);
  }

  /**
   * Write info log on both console and file with given message and method and class name
   *
   * @param message message for info log
   */
  @SneakyThrows
  public static void info(Object message) {
    Log4jLogManager.getLogger().info(getCallInfo() + message);
  }

  /**
   * Write warn log on both console and file with given message and method and class name
   *
   * @param message message for warn log
   */
  public static void warn(Object message) {
    Log4jLogManager.getLogger().warn(getCallInfo() + message);
  }


}
