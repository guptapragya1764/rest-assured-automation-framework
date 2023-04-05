package com.nagp.reports;

import static com.aventstack.extentreports.markuputils.ExtentColor.AMBER;
import static com.aventstack.extentreports.markuputils.ExtentColor.GREEN;
import static com.aventstack.extentreports.markuputils.ExtentColor.RED;
import static com.aventstack.extentreports.markuputils.MarkupHelper.createLabel;
import static com.nagp.reports.ExtentManager.getExtentTest;

/**
 * This class helps in setting logs in Extent Report.
 */
public final class ExtentLogger {

  /**
   * Private constructor to avoid external instantiation
   */
  private ExtentLogger() {

  }

  /**
   * Sets the message for the passed steps.
   *
   * @param message message to appended to with passed step.
   */
  public static void pass(String message) {
    getExtentTest().pass(createLabel(message, GREEN));
  }

  /**
   * Sets the message for the failed steps and create red label for it.
   *
   * @param message message to appended to with failed step.
   */
  public static void fail(String message) {
    getExtentTest().fail(createLabel(message, RED));
  }

  /**
   * Sets the message for the skip steps.
   *
   * @param message message to appended to with skipped step.
   */
  public static void skip(String message) {
    getExtentTest().skip(createLabel(message, AMBER));
  }


  /**
   * Set the info for any step.
   *
   * @param message message that need to pass as info.
   */
  public static void info(String message) {
    try {
      getExtentTest().info(message);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }
  }


}
