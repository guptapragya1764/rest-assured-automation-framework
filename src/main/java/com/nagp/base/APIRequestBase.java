package com.nagp.base;

import static com.nagp.base.ApplicationBase.getValue;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;

/**
 * This class Serves as Base for creating API Request.
 */
@JsonInclude(Include.NON_EMPTY)
public final class APIRequestBase {

  /**
   * Private constructor to avoid external instantiation.
   */
  private APIRequestBase() {

  }

  public static RequestSpecification doRequest(Object body, String sessionToken) {
    return given()
        .log()
        .all()
        .baseUri(getValue("baseUrl"))
        .contentType(JSON)
        .header("Authorization", getValue("appToken"))
        .header("User-Token", sessionToken)
        .body(body);
  }

  public static RequestSpecification doRequest(Object body, String sessionToken,
      PrintStream requestCapture) {
    return doRequest(body, sessionToken).filter(new RequestLoggingFilter(requestCapture));
  }

  public static Response doPost(String url, RequestSpecification requestSpecification) {
    return doResponse(requestSpecification.request("Post", url));
  }

  public static Response doPut(String url, RequestSpecification requestSpecification) {
    return doResponse(requestSpecification.request("Put", url));
  }

  public static Response doDelete(String url, RequestSpecification requestSpecification) {
    return doResponse(requestSpecification.request("Delete", url));
  }

  public static Response doGet(String url, RequestSpecification requestSpecification) {
    return doResponse(requestSpecification.request("Get", url));
  }

  private static Response doResponse(Response response) {
    return response.then().log().all().extract().response();
  }

}
