package com.nagp.factory;

import static com.nagp.constants.MessageConstants.ERROR_MESSAGE_FILE_NOT_FOUND;
import static java.lang.ClassLoader.getSystemClassLoader;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import com.nagp.exceptions.IncorrectFilePathException;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

/**
 * This class acts as factory for producing File Object.
 */
public final class FilesFactory {

  /**
   * Private constructor to avoid external instantiation.
   */
  private FilesFactory() {
  }

  /**
   * Receives json file and covert its data into String
   *
   * @param filePath Path where json data resides
   * @return json data in String form
   */
  @SneakyThrows
  public static String readJsonAndGetAsString(String filePath) {
    return new String(Files.readAllBytes(Paths.get(filePath)));
  }

  /**
   * Receives API response and store it into file
   *
   * @param filePath Path where you want to your response
   * @param response Response received from API
   */
  @SneakyThrows
  public static void storeStringAsJsonFile(String filePath, Response response) {
    Files.write(Paths.get(filePath), response.asByteArray());

  }

  /**
   * Deletes files within folder
   *
   * @param filePath path from where you want to delete file
   */
  public static void deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      try {
        FileUtils.delete(file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @param fileName Name should be relative path from resources folder
   * @return {@link File} instance having full file path
   */
  public static File resource(String fileName) {
    File file;
    try {

      file = new File(requireNonNull(getSystemClassLoader().getResource(fileName)).getFile());
    } catch (NullPointerException ex) {
      throw new IncorrectFilePathException(format(ERROR_MESSAGE_FILE_NOT_FOUND, fileName));
    }
    return file;
  }

}
