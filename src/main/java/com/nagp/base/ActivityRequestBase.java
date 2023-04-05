package com.nagp.base;

import static com.nagp.base.APIRequestBase.doDelete;
import static com.nagp.base.APIRequestBase.doGet;
import static com.nagp.base.APIRequestBase.doPut;
import static com.nagp.base.APIRequestBase.doRequest;
import static com.nagp.base.ApplicationBase.getSessionToken;
import static com.nagp.constants.FrameworkConstant.EMPTYSTRING;
import static com.nagp.constants.ResourceConstant.ACTIVITY_BASE_RESOURCE;
import static com.nagp.constants.ResourceConstant.FILTER_PARAM;
import static com.nagp.constants.ResourceConstant.FOLLOW_ACTIVITY_RESOURCE;
import static com.nagp.constants.ResourceConstant.TYPE_PARAM;
import static com.nagp.constants.ResourceConstant.UNFOLLOW_ACTIVITY_RESOURCE;

import io.restassured.response.Response;
import java.io.PrintStream;

/**
 * This class serves as base for Activity related request
 */
public final class ActivityRequestBase {

  /**
   * Private constructor to avoid external instantiation.
   */
  private ActivityRequestBase() {

  }

  //creating getActivity Request
  public static Response getActivity(PrintStream requestCapt) {
    return doGet(ACTIVITY_BASE_RESOURCE,
        doRequest(EMPTYSTRING, getSessionToken(), requestCapt));
  }

  //creating deleteActivity Request
  public static Response deleteActivity(String id, PrintStream reqCapture) {
    return doDelete(ACTIVITY_BASE_RESOURCE + id,
        doRequest(EMPTYSTRING, getSessionToken(), reqCapture));

  }

  //creating followActivity Request
  public static Response followActivity(String type, String name, PrintStream reqCapture) {
    return doPut(FOLLOW_ACTIVITY_RESOURCE + TYPE_PARAM + type + FILTER_PARAM + name,
        doRequest(EMPTYSTRING, getSessionToken(), reqCapture));

  }

  //creating unFollowActivity Request
  public static Response unfollowActivity(String type, String name, PrintStream reqCapture) {
    return doPut(UNFOLLOW_ACTIVITY_RESOURCE + TYPE_PARAM + type + FILTER_PARAM + name,
        doRequest(EMPTYSTRING, getSessionToken(), reqCapture));

  }

}
