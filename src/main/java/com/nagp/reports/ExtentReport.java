package com.nagp.reports;

import static com.aventstack.extentreports.reporter.configuration.Theme.DARK;
import static com.nagp.constants.FrameworkConstant.EXTENT_REPORT_DOCUMENT_TITLE;
import static com.nagp.constants.FrameworkConstant.EXTENT_REPORT_NAME;
import static com.nagp.constants.FrameworkConstant.getCurrentExtentReportPath;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Perform initialisation and termination of {@link com.aventstack.extentreports.ExtentReports}
 * <p>
 * After creating an instance for {@link com.aventstack.extentreports.ExtentTest}, it is delegated
 * to ThreadLocal variable for providing thread safety.
 */
public final class ExtentReport {

  /**
   * Private constructor to avoid external instantiation
   */
  private ExtentReport() {
  }

  private static ExtentReports extentReports;

  /**
   * Set the initial configuration for the Extent Reports and sets the report generation path.
   */
  public static void initializeReport() {
    extentReports = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(getCurrentExtentReportPath());
    spark.config().setTheme(DARK);
    spark.config().setDocumentTitle(EXTENT_REPORT_DOCUMENT_TITLE);
    spark.config().setReportName(EXTENT_REPORT_NAME);
    extentReports.attachReporter(spark);
  }

  /**
   * Flushing the reports ensures extent logs are reflected properly.
   * <p>
   * Opens the report in the default desktop browser
   */
  public static void tearDownReport() {
    extentReports.flush();
    try {
      Desktop.getDesktop().browse(new File(getCurrentExtentReportPath()).toURI());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a test node in the extent report. Delegates to ThreadLocal for providing thread safety
   *
   * @param name Test Name that need to be reflected in the extent report
   */
  public static void createTest(String name) {
    try {
      ExtentManager.setExtentTest(extentReports.createTest(name));
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
