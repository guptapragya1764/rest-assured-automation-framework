package com.nagp.utils;


import static com.nagp.constants.FrameworkConstant.DATAGENERATORCONSTATNT;
import static com.nagp.constants.FrameworkConstant.EMPTYSTRING;
import static com.nagp.constants.FrameworkConstant.EXCEL_BLANK_VALUE;
import static com.nagp.constants.FrameworkConstant.SEPERATOR;
import static com.nagp.constants.MessageConstants.ERROR_MESSAGE_FILE_NOT_FOUND;
import static com.nagp.constants.MessageConstants.ERROR_MESSAGE_METHOD_NAME_NOT_FOUND;
import static com.nagp.factory.FilesFactory.resource;
import static com.nagp.utils.JavaUtils.getClassByString;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

import com.nagp.constants.FrameworkConstant;
import com.nagp.exceptions.IncorrectFilePathException;
import com.nagp.exceptions.NoSuchMethodFoundInDataGeneratorClassException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.testng.Assert;

;

/**
 * Fetch the test data from Excel file and save it into Object[].
 */
public final class ExcelDataProviderUtil {

  /**
   * Private constructor to avoid external instantiation
   */
  private ExcelDataProviderUtil() {
  }

  /**
   * Fetch the test data from Excel file and sets the Excel file path and sheet index.
   *
   * @param path  relative path of Excel file, this path is relative to test resources folder.
   * @param index index of the sheet where data is available.
   * @return Object[] of test data fetched from Excel file.
   */
  public static Object[] getData(String path, int index) {
    Sheet sheet = getSheetByIndex(path, index);
    return readData(sheet);
  }

  /**
   * @param excelPath relative path of Excel file, this path is relative to test resources folder.
   * @param index     index of the sheet where data is available.
   * @return {@link Sheet} instance
   */
  private static Sheet getSheetByIndex(String excelPath, int index) {
    return getWorkBook(excelPath).getSheetAt(index);
  }

  /**
   * @param excelPath relative path of Excel file, this path is relative to test resources folder.
   * @return {@link Workbook} instance
   */
  private static Workbook getWorkBook(String excelPath) {
    Workbook wb;
    try {
      wb = WorkbookFactory.create(resource(excelPath));
    } catch (IOException e) {
      throw new IncorrectFilePathException(
          format(ERROR_MESSAGE_FILE_NOT_FOUND, resource(excelPath).toURI().getPath()));

    }
    return wb;
  }

  /**
   * Reads the data from Sheet and convert Excel data into Object[];
   *
   * @param sheet {@link Sheet} instance to read the data.
   * @return Object[] of Excel data.
   */
  private static Object[] readData(Sheet sheet) {
    int rowcount = sheet.getLastRowNum();
    int colRount = sheet.getRow(0).getLastCellNum();
    Object[] dataAll = new Object[rowcount];
    int val = 0;
    for (int i = 1; i < rowcount + 1; i++) {
      Map<String, String> mp = new HashMap<>();
      for (int j = 0; j < colRount; j++) {
        String header = sheet.getRow(0).getCell(j).getStringCellValue();
        Cell cell = sheet.getRow(i).getCell(j);
        String cellVal = "";
        if (cell.getCellType() == NUMERIC) {
          cellVal = NumberToTextConverter.toText(cell.getNumericCellValue());
        }
        if (cell.getCellType() == STRING) {
          cellVal = cell.getStringCellValue();
        }

        mp.put(header, cellVal);


      }
      mp.replaceAll((k, v) -> transformData(v));
      dataAll[val] = mp;
      val++;
    }
    return dataAll;
  }

  /**
   * Check the value of Excel Cell and convert it into dynamic data if any method name is found
   * appended with 'dg:' while placing value in Map.
   * <p>
   * Also checks for predefined constant EXCEL_BLANK_VALUE and covert it into appropriate value
   * while placing value in Map
   *
   * @param value value of Excel cell.
   * @return transformed value
   */
  private static String transformData(String value) {
    if (value != null && value.toLowerCase()
        .startsWith(DATAGENERATORCONSTATNT)) {
      String[] parts = value.split(SEPERATOR);

      Method method;
      try {
        method = requireNonNull(getClassByString(FrameworkConstant.DATA_CLASS)).getMethod(parts[1]);
      } catch (NoSuchMethodException ex) {
        throw new NoSuchMethodFoundInDataGeneratorClassException(
            format(ERROR_MESSAGE_METHOD_NAME_NOT_FOUND, parts[1]));
      }
      try {
        return (String) method.invoke(null);
      } catch (Exception ex) {
        Assert.fail(ex.getMessage());
      }
    }

    return requireNonNull(value).equals(EXCEL_BLANK_VALUE) ? EMPTYSTRING : value;
  }
}
