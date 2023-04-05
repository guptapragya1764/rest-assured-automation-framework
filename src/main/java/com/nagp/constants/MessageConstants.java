package com.nagp.constants;

/**
 * MessageConstants holds the value of all the message constant values used within the framework.
 */
public final class MessageConstants {

  /**
   * Private constructor to avoid external instantiation.
   */
  private MessageConstants() {

  }

  public static final String ERROR_MESSAGE_FILE_NOT_FOUND = "Provided path %s for file is not correct. Please enter the correct path";
  public static final String ERROR_MESSAGE_COPYING_DIR_CONTENTS = "Unable to directory Content from %s to %s. Please check extent the contents of Directory or Directory itself";
  public static final String ERROR_MESSAGE_DELETING_DIR_CONTENTS = "Unable to delete %s from directory %s. Please check";
  public static final String TESTCASE_EXECUTION_START_MESSAGE = "\n\n***** Execution Started : %s *****\n";
  public static final String TESTCASE_EXECUTION_END_MESSAGE = "\n\n***** Execution End : %s is %s *****\n";
  public static final String ERROR_MESSAGE_METHOD_NAME_NOT_FOUND = "Method name %s is not available in DataGenerator class. Please correct the method name in excel file";
}
