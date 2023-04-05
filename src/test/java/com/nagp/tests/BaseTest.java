package com.nagp.tests;

import static com.nagp.log4j.Log4jLogLogger.intializeLogger;
import static com.nagp.reports.ExtentLogger.info;

import com.nagp.log4j.Log4jLogLogger;
import java.io.PrintStream;
import java.io.StringWriter;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected StringWriter writer;
  public PrintStream requestCapture;

  @BeforeMethod
  public void setUp() {
    intializeLogger();
    writer = new StringWriter();
    requestCapture = new PrintStream(new WriterOutputStream(writer), true);
  }


  public static void formatLogInReport(String content) {
    String prettyPrint = content.replace("\n", "<br>");
    info("<pre>" + prettyPrint + "</pres>");
    Log4jLogLogger.info(content);
  }

  public void writeRequestAndResponseInReportAndLog(String request, String response) {
    info("---- Request ---");
    Log4jLogLogger.info("---- Request ---");
    formatLogInReport(request);
    Log4jLogLogger.info("---- Response ---");
    info("---- Response ---");
    formatLogInReport(response);
  }
}
