package ntnu.idi.idatt.utility;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public static String toJson(Object object) {
    return gson.toJson(object);
  }

  public static <T> T fromJson(String json, Class<T> classOfT) {
    return gson.fromJson(json, classOfT);
  }

  public static <T> T fromJson(String json, Type typeOfT) {
    return gson.fromJson(json, typeOfT);
  }

  public static <T> T readFromFile(String filePath, Class<T> classOfT) throws IOException {
    String json = FileUtil.readString(filePath);
    return fromJson(json, classOfT);
  }

  public static <T> T readFromFile(String filePath, Type typeOfT) throws IOException {
    String json = FileUtil.readString(filePath);
    return fromJson(json, typeOfT);
  }

  public static void writeToFile(String filePath, Object object) throws IOException {
    String json = toJson(object);
    FileUtil.writeString(filePath, json);
  }

  public static void writeToFile(Path filePath, Object object) throws IOException {
    String json = toJson(object);
    FileUtil.writeString(filePath, json);
  }

  public static <T> Type getListType(Class<T> clazz) {
    return TypeToken.getParameterized(List.class, clazz).getType();
  }
}
