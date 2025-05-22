package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import ntnu.idi.idatt.exceptions.FileHandlingException;
import ntnu.idi.idatt.exceptions.ReadException;
import ntnu.idi.idatt.exceptions.WriteException;

/**
 * Utility class for reading and writing files.
 */
public class FileUtil {
  public static String readString(String filePath) throws ReadException {
    try {
      Path path = Paths.get(filePath);
      if (!Files.exists(path)) {
        throw new ReadException("File not found: " + filePath);
      }
      return Files.readString(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new ReadException("Error reading file: " + filePath, e);
    }
  }

  public static List<String> readLines(String filePath) throws ReadException {
    try {
      Path path = Paths.get(filePath);
      if (!Files.exists(path)) {
        throw new ReadException("File not found: " + filePath);
      }
      return Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new ReadException("Error reading file: " + filePath, e);
    }
  }

  public static void writeString(String filePath, String content) throws WriteException {
    try {
      Path path = Paths.get(filePath);
      Files.writeString(path, content, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new WriteException("Error writing to file: " + filePath, e);
    }
  }

  public static void writeLines(String filePath, List<String> lines) throws WriteException {
    try {
      Path path = Paths.get(filePath);
      Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new WriteException("Error writing to file: " + filePath, e);
    }
  }

  public static void appendString(String filePath, String content) throws WriteException {
    try {
      Path path = Paths.get(filePath);
      Files.writeString(
          path, content, StandardCharsets.UTF_8,
          StandardOpenOption.APPEND,
          StandardOpenOption.CREATE);
    } catch (IOException e) {
      throw new WriteException("Error appending to file: " + filePath, e);
    }
  }

  public static void createDirectories(String dirPath) throws FileHandlingException {
    try {
      Path path = Paths.get(dirPath);
      Files.createDirectories(path);
    } catch (IOException e) {
      throw new FileHandlingException("Error creating directories: " + dirPath, e);
    }
  }

  /**
   * Checks if a file exists at the specified path.
   *
   * @param filePath the path to the file
   * @return true if the file exists, false otherwise
   */
  public static boolean fileExists(String filePath) {
    Path path = Paths.get(filePath);
    return Files.exists(path);
  }
}
