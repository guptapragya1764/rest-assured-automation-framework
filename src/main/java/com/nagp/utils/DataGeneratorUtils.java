package com.nagp.utils;

import com.github.javafaker.Faker;

/**
 * Utility to generate test data for Test.
 */
public final class DataGeneratorUtils {

  /**
   * Private constructor to avoid external instantiation
   */
  private DataGeneratorUtils() {
  }

  public static String getEmail() {
    return new Faker().internet().emailAddress();
  }

  public static String getFirstName() {
    return new Faker().name().firstName();
  }

  public static String getLastName() {
    return new Faker().name().lastName();
  }

  public static String getName() {
    return new Faker().name().fullName();
  }

  public static String getUsername() {
    return new Faker().name().username().replace(".", "_");

  }

  public static String getPassword() {
    return new Faker().internet().password(5, 10);
  }
}
