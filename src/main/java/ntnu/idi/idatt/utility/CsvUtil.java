package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUtil {
  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE_CHAR = '"';

  public static List<String[]> readCsv(String filePath) throws IOException {
    return readCsv(filePath, DEFAULT_SEPARATOR);
  }

  public static List<String[]> readCsv(String filePath, char separator) throws IOException {
    List<String> lines = FileUtil.readLines(filePath);
    List<String[]> result = new ArrayList<>();

    for (String line : lines) {
      result.add(parseLine(line, separator));
    }

    return result;
  }

  public static void writeCsv(String filepath, List<String[]> data) throws IOException {
    writeCsv(filepath, data, DEFAULT_SEPARATOR);
  }

  public static void writeCsv(String filepath, List<String[]> data, char separator) throws IOException {
    List<String> lines = data.stream()
        .map(row -> formatLine(row, separator, DEFAULT_QUOTE_CHAR))
        .collect(Collectors.toList());

    FileUtil.writeLines(filepath, lines);
  }

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
