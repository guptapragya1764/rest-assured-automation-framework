package com.nagp.utils;

import static com.nagp.utils.ExcelDataProviderUtil.getData;

import com.nagp.annotations.DataProviderAnnotation;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

/**
 * Holds the data provider for the test cases in the framework.
 */
public final class DataProviderUtil {

  /**
   * Acts as a data provider for the test cases
   *
   * @param method holds the information about the testcases at runtime.
   * @return excel file data in form of Object[] and each index contains HashMap.
   */
  @DataProvider(name = "excelDataProvider")
  public Object[] excelDataProvider(Method method) {
    DataProviderAnnotation args = method.getAnnotation(DataProviderAnnotation.class);
    return getData((args.file()), args.sheetIndex());
  }

  /**
   * Acts as a data provider for the test cases
   *
   * @param method holds the information about the testcases at runtime.
   * @return excel file data in form of Object[] and each index contains HashMap.
   * <p>
   * parallel=true indicates that each iteration will run in parallel
   */
  @DataProvider(name = "excelDataProviderParallel", parallel = true)
  public Object[] excelDataProviderParallel(Method method) {
    return excelDataProvider(method);
  }


}
