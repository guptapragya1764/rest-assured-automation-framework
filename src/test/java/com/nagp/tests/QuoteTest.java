package com.nagp.tests;

import static com.nagp.base.QuoteRequestBase.favQuote;
import static com.nagp.base.QuoteRequestBase.hideQuote;
import static com.nagp.base.QuoteRequestBase.listQuote;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import io.restassured.response.Response;
import java.util.Map;
import org.testng.annotations.Test;

public class QuoteTest extends BaseTest {

  @Test(testName = "Validate the API for hiding Quote")
  public void hideQuoteTest() {
    Map<String, Object> map = (Map) listQuote().jsonPath().getList("quotes").get(1);
    Response response = hideQuote(map.get("id").toString(), requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(),200);

    //validating element of response body
    assertEquals(response.jsonPath().get("id"),map.get("id"));;
    assertTrue(response.jsonPath().get("user_details.hidden"));
  }

  @Test(testName = "Validate the API to mark the quote as favourite")
  public void markAQuoteAsFavTest() {
    Map<String, Object> map = (Map) listQuote().jsonPath().getList("quotes").get(1);
    Response response = favQuote(map.get("id").toString(), requestCapture);
    writeRequestAndResponseInReportAndLog(writer.toString(), response.prettyPrint());

    //validating the status code of response
    assertEquals(response.getStatusCode(),200);

    //validating element of response body
    assertEquals(response.jsonPath().get("id"),map.get("id"));;
    assertTrue(response.jsonPath().get("user_details.favorite"));
  }
}
