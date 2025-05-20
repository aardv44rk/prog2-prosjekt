package ntnu.idi.idatt.utility;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Custom object for testing JSON serialization and deserialization.
 * This class is used to create instances of objects that can be serialized
 * and deserialized using the JsonUtil class.
 */
class TestObject {
  String name;
  int value;

  // No-arg constructor for Gson deserialization
  public TestObject() {
  }

  public TestObject(String name, int value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TestObject that = (TestObject) o;
    return value == that.value && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }
}

/**
 * Unit tests for the JsonUtil class.
 */
class JsonUtilTest {

  @TempDir
  Path tempDir;

  // Helper class for testing

  @Test
  void testToJsonAndFromJsonForSimpleObject() {
    TestObject original = new TestObject("TestName", 123);
    String json = JsonUtil.toJson(original);

    assertNotNull(json, "Serialized JSON string should not be null.");
    assertTrue(json.contains("\"name\": \"TestName\""), "JSON should contain name field.");
    assertTrue(json.contains("\"value\": 123"), "JSON should contain value field.");

    TestObject deserialized = JsonUtil.fromJson(json, TestObject.class);
    assertEquals(original, deserialized, "Deserialized object should match original.");
  }

  @Test
  void testToJsonAndFromJsonForListOfObjects() {
    List<TestObject> originalList = Arrays.asList(
        new TestObject("Obj1", 1),
        new TestObject("Obj2", 2));
    String json = JsonUtil.toJson(originalList);
    assertNotNull(json, "Serialized JSON string for list should not be null.");

    Type listType = new TypeToken<List<TestObject>>() {
    }.getType();
    List<TestObject> deserializedList = JsonUtil.fromJson(json, listType);

    assertEquals(originalList, deserializedList, "Deserialized list should match original list.");
  }

  @Test
  void testGetListType() {
    Type expectedType = new TypeToken<List<TestObject>>() {
    }.getType();
    Type actualType = JsonUtil.getListType(TestObject.class);
    assertEquals(expectedType, actualType, "getListType should return the correct parameterized List Type.");
  }

  @Test
  void testWriteToFileAndReadFromFileForSimpleObject() throws IOException {
    Path testFile = tempDir.resolve("simpleObject.json");
    TestObject original = new TestObject("FileTest", 456);

    JsonUtil.writeToFile(testFile.toString(), original);
    assertTrue(Files.exists(testFile), "File should be created by writeToFile.");

    TestObject deserialized = JsonUtil.readFromFile(testFile.toString(), TestObject.class);
    assertEquals(original, deserialized, "Object read from file should match original.");
  }

  @Test
  void testWriteToFileAndReadFromFileForListOfObjects() throws IOException {
    Path testFile = tempDir.resolve("listOfObjects.json");
    List<TestObject> originalList = Arrays.asList(
        new TestObject("FileObj1", 10),
        new TestObject("FileObj2", 20));
    Type listType = JsonUtil.getListType(TestObject.class); // Using the utility method

    JsonUtil.writeToFile(testFile.toString(), originalList);
    assertTrue(Files.exists(testFile), "File for list should be created by writeToFile.");

    List<TestObject> deserializedList = JsonUtil.readFromFile(testFile.toString(), listType);
    assertEquals(originalList, deserializedList, "List read from file should match original list.");
  }

  @Test
  void testReadFromFileWhenFileDoesNotExist() {
    Path nonExistentFile = tempDir.resolve("nonExistent.json");

    IOException classException = assertThrows(IOException.class, () -> {
      JsonUtil.readFromFile(nonExistentFile.toString(), TestObject.class);
    });
    assertTrue(classException.getMessage().contains("File not found"),
        "Exception for class type should indicate file not found.");

    Type listType = new TypeToken<List<TestObject>>() {
    }.getType();
    IOException typeException = assertThrows(IOException.class, () -> {
      JsonUtil.readFromFile(nonExistentFile.toString(), listType);
    });
    assertTrue(typeException.getMessage().contains("File not found"),
        "Exception for generic type should indicate file not found.");
  }
}