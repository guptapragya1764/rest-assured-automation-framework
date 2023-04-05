package com.nagp.tests;

import static com.nagp.base.ApplicationBase.getValue;
import static com.nagp.base.UserRequestBase.createUser;
import static com.nagp.base.UserRequestBase.getUser;
import static com.nagp.log4j.Log4jLogLogger.startTestCase;
import static com.nagp.reports.ExtentReport.createTest;
import static org.testng.Assert.assertEquals;

import com.nagp.annotations.DataProviderAnnotation;
import com.nagp.utils.DataProviderUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

  @Test(testName = "Validate API for creating users", dataProvider = "excelDataProviderParallel", dataProviderClass = DataProviderUtil.class)
  @DataProviderAnnotation(file = "testdata/CreateUser.xlsx", sheetIndex = 0)
  public void createUserTest(Map<String, String> dataMap) {
    createTest(dataMap.get("description"));
    startTestCase(dataMap.get("description"));

    Response response = createUser(dataMap.get("login"), dataMap.get("email"),
        dataMap.get("password"), requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    //validating response for positive and negative scenario coming through excel file
    if (dataMap.get("expectedMessage").equals("No Message")) {
      assertEquals(response.jsonPath().getString("login"), dataMap.get("login"));
    } else {
      assertEquals(response.jsonPath().getString("message"), dataMap.get("expectedMessage"));
    }
  }

  @Test(testName = "Validate API for getting users")
  public void getUserTest() {
    Response response = getUser(getValue("login"), requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    //validating schema of getUser response.
    JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUserResponseSchema.json");

    //validating element of response body
    assertEquals(response.jsonPath().getString("account_details.email"), getValue("email"));
    assertEquals(response.jsonPath().getString("login"), getValue("login"));

  }

}
