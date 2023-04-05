package com.nagp.base;

import static com.nagp.base.APIRequestBase.doGet;
import static com.nagp.base.APIRequestBase.doPost;
import static com.nagp.base.APIRequestBase.doRequest;
import static com.nagp.base.ApplicationBase.getSessionToken;
import static com.nagp.constants.FrameworkConstant.EMPTYSTRING;
import static com.nagp.constants.ResourceConstant.SESSION_RESOURCE;
import static com.nagp.constants.ResourceConstant.USER_RESOURCE;

import io.restassured.response.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class serves as base for User related request
 *
 */
public final class UserRequestBase {

  /**
   * Private constructor to avoid external instantiation.
   */
  private UserRequestBase() {
  }

  //creating createUser Request
  public static Response createUser(String login, String email, String password,
      PrintStream requestCapt) {
    Map<String, Object> user = new HashMap<>();
    user.put("login", login);
    user.put("email", email);
    user.put("password", password);
    Map<String, Object> createUser = new HashMap<>();
    createUser.put("user", user);
    return doPost(USER_RESOURCE, doRequest(createUser, EMPTYSTRING, requestCapt));
  }

  //creating createSession Request
  public static Response createSession(String login, String password) {
    Map<String, Object> user = new HashMap<>();
    user.put("login", login);
    user.put("password", password);
    Map<String, Object> createSession = new HashMap<>();
    createSession.put("user", user);
    return doPost(SESSION_RESOURCE, doRequest(createSession, EMPTYSTRING));
  }

  //creating getUser Request
  public static Response getUser(String login, PrintStream requestCapt) {
    return doGet(USER_RESOURCE + login,
        doRequest(EMPTYSTRING, getSessionToken(), requestCapt));
  }

}
