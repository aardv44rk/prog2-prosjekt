package ntnu.idi.idatt.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvUtilTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadCsvDefaultSeparator() throws IOException {
        Path csvFile = tempDir.resolve("testReadDefault.csv");
        List<String> lines = Arrays.asList(
                "name,age,city", 
                "Kari,30,Oslo",
                "\"Ola, Jr.\",25,\"Bergen\"",
                "Per,,Trondheim", // Empty field for age
                "Liv,40, " // Last field with a space
        );
        Files.write(csvFile, lines);

        List<String[]> result = CsvUtil.readCsv(csvFile.toString());

        assertEquals(5, result.size(), "Should read 5 lines including header.");
        assertArrayEquals(new String[]{"name", "age", "city"}, result.get(0), "Header row should match.");
        assertArrayEquals(new String[]{"Kari", "30", "Oslo"}, result.get(1), "First data row should match.");
        assertArrayEquals(new String[]{"Ola, Jr.", "25", "Bergen"}, result.get(2), "Second data row with quotes and comma should match.");
        assertArrayEquals(new String[]{"Per", "", "Trondheim"}, result.get(3), "Third data row with empty field should match.");
        assertArrayEquals(new String[]{"Liv", "40", " "}, result.get(4), "Fourth data row with trailing space should match.");
    }

    @Test
    void testReadCsvTrailingEmptyField() throws IOException {
        Path csvFile = tempDir.resolve("testReadTrailing.csv");
        List<String> lines = Arrays.asList("Erik,40,"); 
        Files.write(csvFile, lines);

        List<String[]> result = CsvUtil.readCsv(csvFile.toString());
        assertEquals(1, result.size(), "Should read 1 line.");
        assertArrayEquals(new String[]{"Erik", "40", ""}, result.get(0), "Row with trailing empty field should parse correctly.");
    }

    @Test
    void testReadCsvCustomSeparator() throws IOException {
        Path csvFile = tempDir.resolve("testReadCustom.csv");
        char separator = ';';
        List<String> lines = Arrays.asList(
                "name;age;city",
                "Kari;30;Oslo",
                "\"Ola; Jr.\";25;\"Bergen\""
        );
        Files.write(csvFile, lines);

        List<String[]> result = CsvUtil.readCsv(csvFile.toString(), separator);

        assertEquals(3, result.size(), "Should read 3 lines with custom separator.");
        assertArrayEquals(new String[]{"name", "age", "city"}, result.get(0), "Header row with custom separator should match.");
        assertArrayEquals(new String[]{"Kari", "30", "Oslo"}, result.get(1), "First data row with custom separator should match.");
        assertArrayEquals(new String[]{"Ola; Jr.", "25", "Bergen"}, result.get(2), "Second data row with quotes and custom separator should match.");
    }

    @Test
    void testWriteCsvDefaultSeparator() throws IOException {
        Path csvFile = tempDir.resolve("testWriteDefault.csv");
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"name", "age", "city"});
        data.add(new String[]{"Kari", "30", "Oslo"});
        data.add(new String[]{"Ola, Jr.", "25", "Bergen"}); 
        data.add(new String[]{"Per", "35", "Trondheim \"Nidaros\""});

        CsvUtil.writeCsv(csvFile.toString(), data);

        List<String> expectedLines = Arrays.asList(
                "name,age,city",
                "Kari,30,Oslo",
                "\"Ola, Jr.\",25,Bergen",
                "Per,35,\"Trondheim \"\"Nidaros\"\"\"" // Quotes should be escaped
        );
        List<String> actualLines = Files.readAllLines(csvFile);
        assertEquals(expectedLines, actualLines, "Written CSV lines should match expected format.");
    }

    @Test
    void testWriteCsvCustomSeparator() throws IOException {
        Path csvFile = tempDir.resolve("testWriteCustom.csv");
        char separator = ';';
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"name", "age", "city"});
        data.add(new String[]{"Kari", "30", "Oslo"});
        data.add(new String[]{"Ola; Jr.", "25", "Bergen"}); // Separator here

        CsvUtil.writeCsv(csvFile.toString(), data, separator);

        List<String> expectedLines = Arrays.asList(
                "name;age;city",
                "Kari;30;Oslo",
                "\"Ola; Jr.\";25;Bergen" // Separator within field should be quoted
        );
        List<String> actualLines = Files.readAllLines(csvFile);
        assertEquals(expectedLines, actualLines, "Written CSV lines with custom separator should match expected format.");
    }
    
    @Test
    void testReadCsvEscapedQuotes() throws IOException {
        Path csvFile = tempDir.resolve("testReadEscapedQuotes.csv");
        List<String> lines = Arrays.asList(
                "description,notes",
                "\"Item with \"\"quotes\"\" in the description\",\"Notes are easy!\""
        );
        Files.write(csvFile, lines);

        List<String[]> result = CsvUtil.readCsv(csvFile.toString());
        assertEquals(2, result.size(), "Should read 2 lines for escaped quotes test.");
        assertArrayEquals(new String[]{"description", "notes"}, result.get(0), "Header row for escaped quotes test should match.");
        assertArrayEquals(new String[]{"Item with \"quotes\" in the description", "Notes are easy!"}, result.get(1), "Data row with escaped quotes should parse correctly.");
    }

    @Test
    void testReadCsvFileDoesNotExist() {
        Path nonExistentFile = tempDir.resolve("nonExistent.csv");
        IOException exception = assertThrows(IOException.class, () -> {
            CsvUtil.readCsv(nonExistentFile.toString());
        }, "Reading a non-existent CSV file should throw IOException.");
        assertTrue(exception.getMessage().contains("Error reading file"), "Exception message should indicate file not found.");
    }
    
    @Test
    void testWriteCsvNullValues() throws IOException {
        Path csvFile = tempDir.resolve("testWriteNull.csv");
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id", "value", "comment"});
        // Adding null values
        data.add(new String[]{"1", null, "Null value"});
        data.add(new String[]{null, "Data", null}); 

        CsvUtil.writeCsv(csvFile.toString(), data);

        List<String> expectedLines = Arrays.asList(
                "id,value,comment",
                "1,,Null value", // Null should be written as empty string
                ",Data," // Null should be written as empty string
        );
        List<String> actualLines = Files.readAllLines(csvFile);
        assertEquals(expectedLines, actualLines, "CSV lines with null values should be written correctly.");
  }
}