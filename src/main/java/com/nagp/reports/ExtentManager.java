package com.nagp.reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentManager class helps to achieve thread safety for the {@link
 * com.aventstack.extentreports.ExtentTest} instance.
 */

public final class ExtentManager {

  /**
   * Private constructor to avoid external instantiation
   */
  private ExtentManager() {
  }

  private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

  /**
   * Return thread safe {@link com.aventstack.extentreports.ExtentTest} instance fetched from
   * ThreadLocal variable
   *
   * @return Thread safe ExtentTest instance
   */


  static ExtentTest getExtentTest() {
    return exTest.get();
  }

  /**
   * Set the {@link com.aventstack.extentreports.ExtentTest} instance to thread local variable
   *
   * @param test ExtentTest instance that needs to be saved from thread safety issue
   */
  static void setExtentTest(ExtentTest test) {
    exTest.set(test);
  }
}
