package com.nagp.listeners;

import static com.nagp.constants.FrameworkConstant.getArchivedLogFolderPath;
import static com.nagp.constants.FrameworkConstant.getArchivedTestResultPath;
import static com.nagp.constants.FrameworkConstant.getCurrentLogFolderPath;
import static com.nagp.constants.FrameworkConstant.getCurrentTestResultPath;
import static com.nagp.constants.FrameworkConstant.getLogFolderPath;
import static com.nagp.factory.FilesFactory.deleteFile;
import static com.nagp.log4j.Log4jLogLogger.endTestCase;
import static com.nagp.log4j.Log4jLogLogger.startTestCase;
import static com.nagp.reports.ExtentLogger.fail;
import static com.nagp.reports.ExtentLogger.pass;
import static com.nagp.reports.ExtentLogger.skip;
import static com.nagp.reports.ExtentReport.createTest;
import static com.nagp.reports.ExtentReport.initializeReport;
import static com.nagp.reports.ExtentReport.tearDownReport;
import static com.nagp.utils.TestResultUtils.moveFoldersContents;
import static java.lang.String.valueOf;

import lombok.SneakyThrows;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Implements {@link org.testng.ITestListener} and {@link org.testng.ISuiteListener} to leverage the
 * abstract methods to get help in extent report generation
 */

public class FrameworkListener implements ITestListener, ISuiteListener {

  /**
   * Initialise the reports with the file name.
   * <p>
   * Moves 'current-folder-result' content to 'archived-test-result' before the start of test case
   * execution.
   * <p>
   * Moves 'current' logs to 'archived' logs before the start of test case execution.
   */
  @Override
  public void onStart(ISuite suite) {
    moveFoldersContents(getCurrentTestResultPath(), getArchivedTestResultPath());
    moveFoldersContents(getLogFolderPath() + getCurrentLogFolderPath(),
        getArchivedLogFolderPath());
    initializeReport();
  }

  /**
   * Terminates the report.
   */
  @Override
  public void onFinish(ISuite suite) {
    tearDownReport();
    deleteFile(getLogFolderPath() + "${ctx");
  }

  /**
   * Starts a test node for each testng test and start logger for each testcase from here.
   */
  @Override
  @SneakyThrows
  public void onTestStart(ITestResult result) {
    if (!result.getMethod().isDataDriven()) {
      String testName = result.getMethod().getConstructorOrMethod().getMethod()
          .getAnnotation(Test.class).testName();
      startTestCase(testName);
      createTest(testName);
    }
  }

  /**
   * Marks the test as pass and logs it in the report and logger.
   */
  @Override
  public void onTestSuccess(ITestResult result) {
    pass(result.getName() + " is passed");
    endTestCase(result.getMethod().getMethodName(), "passed");
  }

  /**
   * Marks the test as fail and logs it in the report and logger.
   */
  @Override
  public void onTestFailure(ITestResult result) {
    fail(valueOf(result.getThrowable()));
    endTestCase(result.getMethod().getMethodName(), "failed");
  }

  /**
   * Marks the test as skip and logs it in the report and logger.
   */
  @Override
  public void onTestSkipped(ITestResult result) {
    skip(result.getName() + " is skipped");
    endTestCase(result.getMethod().getMethodName(), "skipped");
  }

}
