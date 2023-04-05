package com.nagp.base;

import static com.nagp.base.UserRequestBase.createSession;
import static com.nagp.constants.FrameworkConstant.CONFIGFILEPATH;
import static java.lang.String.valueOf;

import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class reads properties from config file, store in HashMap and expose these properties
 * through getValue method
 * <p>
 * Also creates session Token
 */
public class ApplicationBase {

  /**
   * Private constructor to avoid external instantiation.
   */
  private ApplicationBase() {
  }

  private static Properties properties = new Properties();
  private static Map<String, String> map = new HashMap();
  private static String sessionToken;

  static {
    try {
      FileInputStream fileInputStream = new FileInputStream(CONFIGFILEPATH);
      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
    properties.forEach((key, value) -> map.put(valueOf(key), valueOf(value)));
  }

  public static String getValue(String key) {
    return map.get(key);
  }

  static {
    Response response = createSession(getValue("login"), getValue("password"));
    sessionToken = response.jsonPath().getString("User-Token");
  }

  public static String getSessionToken() {
    return sessionToken == null ? getSessionToken() : sessionToken;
  }
}
