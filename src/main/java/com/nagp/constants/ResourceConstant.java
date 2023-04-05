package com.nagp.constants;

/**
 * FrameworkConstant holds the value of all the API resource constant values used within the
 * framework.
 */
public final class ResourceConstant {

  /**
   * Private constructor to avoid external instantiation.
   */
  private ResourceConstant() {

  }

  private static final String BASE_RESOURCE = "/api/";


  //Users
  public static final String SESSION_RESOURCE = BASE_RESOURCE + "session";
  public static final String USER_RESOURCE = BASE_RESOURCE + "users/";

  //Activities
  public static final String ACTIVITY_BASE_RESOURCE = BASE_RESOURCE + "activities/";
  public static final String FOLLOW_ACTIVITY_RESOURCE = ACTIVITY_BASE_RESOURCE + "follow/?";
  public static final String UNFOLLOW_ACTIVITY_RESOURCE = ACTIVITY_BASE_RESOURCE + "unfollow/?";
  public static final String TYPE_PARAM = "type=";
  public static final String FILTER_PARAM = "&filter=";

  //Quotes
  public static final String QUOTE_BASE_RESOURCE = BASE_RESOURCE + "quotes/";
  public static final String HIDE_QUOTE_RESOURCE = "/hide/";
  public static final String FAV_QUOTE_RESOURCE = "/fav/";
}
