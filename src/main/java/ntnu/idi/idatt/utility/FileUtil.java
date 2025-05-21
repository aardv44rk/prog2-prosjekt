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
    // Private constructor to prevent instantiation
  }

  private static final String SAVES_DIRECTORY = "saves";

  private static void ensureSaveDirExists() throws IOException {
    Path saveDir = Paths.get(SAVES_DIRECTORY);
    if (!Files.exists(saveDir)) {
      Files.createDirectories(saveDir);
      System.out.println("Created save directory: " + SAVES_DIRECTORY);
    }
  }

  public static Path getSavePath(String fileName) throws IOException {
    return Paths.get(SAVES_DIRECTORY, fileName);
  }


  public static String readString(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      throw new IOException("File not found: " + filePath);
    }
    return Files.readString(path, StandardCharsets.UTF_8);
  }

  public static String readString(Path filePath) throws IOException {
    return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
  }

  public static List<String> readLines(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      throw new IOException("File not found: " + filePath);
    }
    return Files.readAllLines(path, StandardCharsets.UTF_8);
  }

  public static void writeString(String filePath, String content) throws IOException {
    ensureSaveDirExists();
    Path path = Paths.get(filePath);
    Files.writeString(path, content, StandardCharsets.UTF_8);
  }

  public static void writeString(Path filePath, String content) throws IOException {
    ensureSaveDirExists();
    Files.writeString(filePath, content, StandardCharsets.UTF_8);
  }

  public static void writeLines(String filePath, List<String> lines) throws IOException {
    ensureSaveDirExists();
    Path path = Paths.get(filePath);
    Files.write(path, lines, StandardCharsets.UTF_8);
  }

  public static void appendString(String filePath, String content) throws IOException {
    Path path = Paths.get(filePath);
    Files.writeString(
      path, content, StandardCharsets.UTF_8,
      StandardOpenOption.APPEND,
      StandardOpenOption.CREATE
    );
  }

  public static void createDirectories(String dirPath) throws IOException {
    Path path = Paths.get(dirPath);
    Files.createDirectories(path);
  }

  public static boolean fileExists(String filePath) {
    Path path = Paths.get(filePath);
    return Files.exists(path);
  }
}
