package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Utility class for reading and writing files.
 */
public class FileUtil {
  private FileUtil() {
    // Prevent instantiation
  }

  /**
   * Reads the content of a file as a string.
   *
   * @param filePath the path to the file
   * @return the content of the file as a string
   * @throws IOException if an I/O error occurs
   */
  public static String readString(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      throw new IOException("File not found: " + filePath);
    }
    return Files.readString(path, StandardCharsets.UTF_8);
  }

  /**
   * Reads the content of a file as a list of strings, where each string represents a line in the
   * file.
   *
   * @param filePath the path to the file
   * @return the content of the file as a list of strings
   * @throws IOException if an I/O error occurs
   */
  public static List<String> readLines(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      throw new IOException("File not found: " + filePath);
    }
    return Files.readAllLines(path, StandardCharsets.UTF_8);
  }

  /**
   * Writes a string to a file.
   *
   * @param filePath the path to the file
   * @param content  the content to write to the file
   * @throws IOException if an I/O error occurs
   */
  public static void writeString(String filePath, String content) throws IOException {
    Path path = Paths.get(filePath);
    Files.writeString(path, content, StandardCharsets.UTF_8);
  }

  /**
   * Writes a list of strings to a file, where each string represents a line in the file.
   *
   * @param filePath the path to the file
   * @param lines    the content to write to the file
   * @throws IOException if an I/O error occurs
   */
  public static void writeLines(String filePath, List<String> lines) throws IOException {
    Path path = Paths.get(filePath);
    Files.write(path, lines, StandardCharsets.UTF_8);
  }

  /**
   * Appends a string to a file.
   *
   * @param filePath the path to the file
   * @param content  the content to append to the file
   * @throws IOException if an I/O error occurs
   */
  public static void appendString(String filePath, String content) throws IOException {
    Path path = Paths.get(filePath);
    Files.writeString(
      path, content, StandardCharsets.UTF_8,
      StandardOpenOption.APPEND,
      StandardOpenOption.CREATE
    );
  }

  /**
   * Creates a directory at the specified path.
   *
   * @param dirPath the path to the directory
   * @throws IOException if an I/O error occurs
   */
  public static void createDirectories(String dirPath) throws IOException {
    Path path = Paths.get(dirPath);
    Files.createDirectories(path);
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
