package com.nagp.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Framework Annotation is user built annotation which is annotated on top of test methods to feed
 * the data to test method by providing Excel file path.
 * <p>
 * Runtime retention value indicate that this annotation will be available at run time.
 * <p>
 * Method target value indicate this annotation will only be available at method level
 */

@Retention(RUNTIME)
@Target(METHOD)
public @interface DataProviderAnnotation {

  /**
   * @return path of Excel file. Path of the file provided should be relative to test resource
   * folder.
   */

  String file();

  /**
   * @return index of the file where data is present.
   */
  int sheetIndex();
}
