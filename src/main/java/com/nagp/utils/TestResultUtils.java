package com.nagp.utils;

import static com.nagp.constants.MessageConstants.ERROR_MESSAGE_COPYING_DIR_CONTENTS;
import static com.nagp.constants.MessageConstants.ERROR_MESSAGE_DELETING_DIR_CONTENTS;
import static com.nagp.log4j.Log4jLogLogger.info;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.io.FileUtils.copyDirectory;
import static org.apache.commons.io.FileUtils.delete;
import static org.apache.commons.io.FileUtils.deleteDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Utility Class for generating artifacts.
 */
public final class TestResultUtils {

  private TestResultUtils() {

  }

  /**
   * Moves source folder content to destination folder.
   */
  public static void moveFoldersContents(String srcDir, String destDir) {
    File srcFile = new File(srcDir);
    File destFile = new File(destDir);
    try {
      copyDirectory(srcFile, destFile);
    } catch (IOException e) {
      e.printStackTrace();
//      info(format(ERROR_MESSAGE_COPYING_DIR_CONTENTS, srcDir, srcFile));
    }
    if (srcFile.exists()) {
      for (File file : requireNonNull(srcFile.listFiles())) {
        try {
          if (file.isDirectory()) {
            deleteDirectory(file);
          } else {
            delete(file);
          }
        } catch (IOException ex) {
          info(format(ERROR_MESSAGE_DELETING_DIR_CONTENTS, file, srcFile));
        }
      }
    }
  }

}
