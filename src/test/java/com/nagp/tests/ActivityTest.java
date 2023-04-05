package com.nagp.tests;

import static com.nagp.base.ActivityRequestBase.deleteActivity;
import static com.nagp.base.ActivityRequestBase.followActivity;
import static com.nagp.base.ActivityRequestBase.getActivity;
import static com.nagp.base.ActivityRequestBase.unfollowActivity;
import static com.nagp.base.QuoteRequestBase.listQuote;
import static com.nagp.base.UserRequestBase.createUser;
import static com.nagp.utils.DataGeneratorUtils.getEmail;
import static com.nagp.utils.DataGeneratorUtils.getPassword;
import static com.nagp.utils.DataGeneratorUtils.getUsername;
import static org.testng.Assert.assertEquals;

import com.nagp.response.GetActivityResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.annotations.Test;

public class ActivityTest extends BaseTest {


  @Test(testName = "Validating the API for getting activity")
  public void getActivityTest() {
    Response response = getActivity(requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    // validating page object of response by pojo
    assertEquals(response.as(GetActivityResponse.class).getPage(), 1);
  }

  @Test(dependsOnMethods = {"followActivityTest",
      "unfollowActivityTest"}, testName = "Validate the API for delete activity", alwaysRun = true)
  public void deleteActivityTest() {
    String activityId = getActivity(requestCapture).jsonPath()
        .getString("activities[0].activity_id");
    Response response = deleteActivity(activityId, requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    //validating response body
    assertEquals(response.jsonPath().getString("status"), "ok");
  }

  @Test(testName = "Validate the API for follow activity")
  public void followActivityTest() {
    String login = getUsername();

    createUser(login, getEmail(), getPassword(), requestCapture);
    Response response = followActivity("user", login, requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    //validating response body
    assertEquals(response.jsonPath().getString("status"), "ok");
  }

  @Test(dependsOnMethods = "followActivityTest", testName = "Validate the API for unfollow activity")
  public void unfollowActivityTest() {
    Map<String, Object> map = (Map) listQuote().jsonPath().getList("quotes").get(0);
    followActivity("author", map.get("author").toString(), requestCapture);
    Response response = unfollowActivity("author", map.get("author").toString(), requestCapture);

    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(), 200);

    //validating response body
    assertEquals(response.jsonPath().getString("status"), "ok");
  }
}
