package com.nagp.base;

import static com.nagp.base.APIRequestBase.doGet;
import static com.nagp.base.APIRequestBase.doPut;
import static com.nagp.base.APIRequestBase.doRequest;
import static com.nagp.base.ApplicationBase.getSessionToken;
import static com.nagp.constants.FrameworkConstant.EMPTYSTRING;
import static com.nagp.constants.ResourceConstant.FAV_QUOTE_RESOURCE;
import static com.nagp.constants.ResourceConstant.HIDE_QUOTE_RESOURCE;
import static com.nagp.constants.ResourceConstant.QUOTE_BASE_RESOURCE;

import io.restassured.response.Response;
import java.io.PrintStream;

/**
 * This class serves as base for Quote related request
 */
public final class QuoteRequestBase {

  /**
   * Private constructor to avoid external instantiation.
   */
  private QuoteRequestBase() {

  }

  //creating hideQuote Request
  public static Response hideQuote(String id, PrintStream reqCapture) {
    return doPut(QUOTE_BASE_RESOURCE + id + HIDE_QUOTE_RESOURCE,
        doRequest(EMPTYSTRING, getSessionToken(), reqCapture));

  }

  //creating favQuote Request
  public static Response favQuote(String id, PrintStream reqCapture) {
    return doPut(QUOTE_BASE_RESOURCE + id + FAV_QUOTE_RESOURCE,
        doRequest(EMPTYSTRING, getSessionToken(), reqCapture));
  }

  //creating listQuote Request
  public static Response listQuote() {
    return doGet(QUOTE_BASE_RESOURCE,
        doRequest(EMPTYSTRING, getSessionToken()));
  }

}
