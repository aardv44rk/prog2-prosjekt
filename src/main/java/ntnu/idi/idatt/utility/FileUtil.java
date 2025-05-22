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
  /**
   * Reads the content of a file as a string.
   * 
   * @param filePath the path to the file
   * @return the content of the file as a string
   * @throws ReadException if an error occurs while reading the file
   */
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

  /**
   * Reads the content of a file as a list of strings.
   * 
   * @param filePath the path to the file
   * @return the content of the file as a list of strings
   * @throws ReadException if an error occurs while reading the file
   */
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

  /**
   * Writes a string to a file.
   * 
   * @param filePath the path to the file
   * @param content the content to write
   * @throws WriteException if an error occurs while writing to the file
   */
  public static void writeString(String filePath, String content) throws WriteException {
    try {
      Path path = Paths.get(filePath);
      Files.writeString(path, content, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new WriteException("Error writing to file: " + filePath, e);
    }
  }

  /**
   * Writes a list of strings to a file.
   * 
   * @param filePath the path to the file
   * @param lines the lines to write
   * @throws WriteException if an error occurs while writing to the file
   */
  public static void writeLines(String filePath, List<String> lines) throws WriteException {
    try {
      Path path = Paths.get(filePath);
      Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new WriteException("Error writing to file: " + filePath, e);
    }
  }

  /**
   * Appends a string to a file.
   * 
   * @param filePath the path to the file
   * @param content the content to append
   * @throws WriteException if an error occurs while appending to the file
   */
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

  /**
   * Creates directories at the specified path.
   * 
   * @param dirPath the path to the directory
   * @throws FileHandlingException if an error occurs while creating the directories
   */
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
