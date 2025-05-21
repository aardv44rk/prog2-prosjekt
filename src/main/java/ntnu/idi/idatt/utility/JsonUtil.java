package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Utility class for reading and writing JSON files.
 */
public class JsonUtil {
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private JsonUtil() {
    // Prevent instantiation
  }

  /**
   * Converts an object to its JSON representation.
   *
   * @param object the object to convert
   * @return the JSON representation of the object
   */
  public static String toJson(Object object) {
    return gson.toJson(object);
  }

  /**
   * Converts a JSON string to an object of the specified class.
   *
   * @param json     the JSON string to convert
   * @param classOfT the class of the object to convert to
   * @return the converted object
   */
  public static <T> T fromJson(String json, Class<T> classOfT) {
    return gson.fromJson(json, classOfT);
  }

  /**
   * Converts a JSON string to an object of the specified type.
   *
   * @param json     the JSON string to convert
   * @param typeOfT  the type of the object to convert to
   * @return the converted object
   */
  public static <T> T fromJson(String json, Type typeOfT) {
    return gson.fromJson(json, typeOfT);
  }

  /**
   * Reads a JSON file and converts it to an object of the specified class.
   *
   * @param filePath the path to the JSON file
   * @param classOfT the class of the object to convert to
   * @return the converted object
   * @throws IOException if an I/O error occurs
   */
  public static <T> T readFromFile(String filePath, Class<T> classOfT) throws IOException {
    String json = FileUtil.readString(filePath);
    return fromJson(json, classOfT);
  }

  /**
   * Reads a JSON file and converts it to an object of the specified type.
   *
   * @param filePath the path to the JSON file
   * @param typeOfT  the type of the object to convert to
   * @return the converted object
   * @throws IOException if an I/O error occurs
   */
  public static <T> T readFromFile(String filePath, Type typeOfT) throws IOException {
    String json = FileUtil.readString(filePath);
    return fromJson(json, typeOfT);
  }

  /**
   * Writes an object to a JSON file.
   *
   * @param filePath the path to the JSON file
   * @param object   the object to write
   * @throws IOException if an I/O error occurs
   */
  public static void writeToFile(String filePath, Object object) throws IOException {
    String json = toJson(object);
    FileUtil.writeString(filePath, json);
  }

  /**
   * Gets the type of a list of the specified class.
   *
   * @param clazz the class of the objects in the list
   * @return the type of the list
   */
  public static <T> Type getListType(Class<T> clazz) {
    return TypeToken.getParameterized(List.class, clazz).getType();
  }
}
