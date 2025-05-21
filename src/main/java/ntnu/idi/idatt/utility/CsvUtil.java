package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for reading and writing CSV files.
 */
public class CsvUtil {
  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE_CHAR = '"';

  private CsvUtil() {
    // Prevent instantiation
  }
  /**
   * Reads a CSV file and returns its content as a list of string arrays.
   *
   * @param filePath the path to the CSV file
   * @return a list of string arrays, where each array represents a row in the CSV file
   * @throws IOException if an I/O error occurs
   */
  public static List<String[]> readCsv(String filePath) throws IOException {
    return readCsv(filePath, DEFAULT_SEPARATOR);
  }

  /**
   * Reads a CSV file with a custom separator and returns its content as a list of string arrays.
   *
   * @param filePath the path to the CSV file
   * @param separator the character used to separate values in the CSV file
   * @return a list of string arrays, where each array represents a row in the CSV file
   * @throws IOException if an I/O error occurs
   */
  public static List<String[]> readCsv(String filePath, char separator) throws IOException {
    List<String> lines = FileUtil.readLines(filePath);
    List<String[]> result = new ArrayList<>();

    for (String line : lines) {
      result.add(parseLine(line, separator));
    }

    return result;
  }

  /**
   * Writes a list of string arrays to a CSV file.
   *
   * @param filepath the path to the CSV file
   * @param data     the data to write to the CSV file
   * @throws IOException if an I/O error occurs
   */
  public static void writeCsv(String filepath, List<String[]> data) throws IOException {
    writeCsv(filepath, data, DEFAULT_SEPARATOR);
  }

  /**
   * Writes a list of string arrays to a CSV file with a custom separator.
   *
   * @param filepath the path to the CSV file
   * @param data     the data to write to the CSV file
   * @param separator the character used to separate values in the CSV file
   * @throws IOException if an I/O error occurs
   */
  public static void writeCsv(String filepath, List<String[]> data, char separator) throws IOException {
    List<String> lines = data.stream()
        .map(row -> formatLine(row, separator, DEFAULT_QUOTE_CHAR))
        .collect(Collectors.toList());

    FileUtil.writeLines(filepath, lines);
  }

  /**
   * Parses a line of CSV data into an array of strings.
   *
   * @param line     the line to parse
   * @param separator the character used to separate values in the CSV line
   * @return an array of strings representing the parsed values
   */
  private static String[] parseLine(String line, char separator) {
    List<String> result = new ArrayList<>();
    StringBuilder currentValue = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);

      if (c == DEFAULT_QUOTE_CHAR) {
        // Handle quotes
        if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == DEFAULT_QUOTE_CHAR) {
          // Handle escaped quote
          currentValue.append(c);
          i++;
        } else {
          // Toggle inQuotes state
          inQuotes = !inQuotes;
        }
      } else if (c == separator && !inQuotes) {
        // End of field
        result.add(currentValue.toString().trim());
        currentValue.setLength(0);
      } else {
        // Add character to current value
        currentValue.append(c);
      }

      
    }

    result.add(currentValue.toString());
    
    return result.toArray(new String[0]);
  }

  /**
   * Formats a string array into a CSV line.
   *
   * @param values   the string array to format
   * @param separator the character used to separate values in the CSV line
   * @param quote    the character used to quote values in the CSV line
   * @return a formatted CSV line as a string
   */
  private static String formatLine(String[] values, char separator, char quote) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < values.length; i++) {
      String value = values[i];
      boolean needQuotes = value != null && (
        value.contains(String.valueOf(separator)) ||
        value.contains(String.valueOf(quote)) ||
        value.contains("\n") ||
        value.contains("\r"));

      if (needQuotes) {
        value = value.replace(String.valueOf(quote), String.valueOf(quote) + quote);
        result.append(quote).append(value).append(quote);
      } else {
        result.append(value == null ? "" : value);
      }

      if (i < values.length - 1) {
        result.append(separator);
      }
    }
    
    return result.toString();
  }
}
