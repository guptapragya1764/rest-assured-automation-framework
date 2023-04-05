package com.nagp.constants;

import static java.lang.System.getProperty;

/**
 * FrameworkConstant holds the value of all the constant values used within the framework.
 */
public final class FrameworkConstant {

  /**
   * Private constructor to avoid external instantiation.
   */
  private FrameworkConstant() {

  }

  public static int MAX_RETRIES = 1;
  public static final String EMPTYSTRING = "";
  public static final String SEPERATOR = ":";
  public static final String DATA_CLASS = "com.nagp.utils.DataGeneratorUtils";
  public static final String EXCEL_BLANK_VALUE = "BLANK_VALUE";
  public static final String DATAGENERATORCONSTATNT = "dg:";
  public static final String EXTENT_REPORT_DOCUMENT_TITLE = "Test Framework Execution Report";
  public static final String EXTENT_REPORT_NAME = "QA_Execution_Report";
  private static final String USERDIR = getProperty("user.dir");
  public static final String RESOURCESPATH = USERDIR + "/src/test/resources/";
  public static final String CONFIGFILEPATH = RESOURCESPATH + "configs/config.properties";
  private static final String TESTRESULTPATH = USERDIR + "/test-results/";
  private static final String CURRENTTESTRESULTPATH = TESTRESULTPATH + "current-test-results/";
  private static final String ARCHIVEDTESTRESULTPATH = TESTRESULTPATH + "archived-test-results/";
  private static final String EXTENTREPORTPATH = "/extent-report/index.html";
  private static final String LOGFOLDERPATH = USERDIR + "/logs/";
  private static final String CURRENTLOGFOLDERPATH = "current/";
  private static long currentTimeStamp = 0;
  private static final String ARCHIVEDLOGFOLDERPATH = LOGFOLDERPATH + "archived";


  public static String getLogFolderPath() {
    return LOGFOLDERPATH;
  }

  public static String getCurrentLogFolderPath() {
    return CURRENTLOGFOLDERPATH;
  }

  public static String getCurrentLogFolderPathWithTimeStamp() {
    return CURRENTLOGFOLDERPATH + getCurrentTimeStamp() + "/";
  }

  public static String getArchivedLogFolderPath() {
    return ARCHIVEDLOGFOLDERPATH;
  }

  private static String getCurrentTestResultPathWithTimeStampFolder() {
    return CURRENTTESTRESULTPATH + getCurrentTimeStamp();
  }

  private static long getCurrentTimeStamp() {
    if (currentTimeStamp == 0) {
      currentTimeStamp = System.currentTimeMillis();
    }
    return currentTimeStamp;

  }

  public static String getCurrentExtentReportPath() {
    return getCurrentTestResultPathWithTimeStampFolder() + EXTENTREPORTPATH;
  }

  public static String getCurrentTestResultPath() {
    return CURRENTTESTRESULTPATH;
  }

  public static String getArchivedTestResultPath() {
    return ARCHIVEDTESTRESULTPATH;
  }


}
