package ntnu.idi.idatt.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the FileUtil class.
 */
class FileUtilTest {

    @TempDir
    Path tempDir; // temporary directory

    @Test
    void testWriteStringAndReadString() throws IOException {
        Path testFile = tempDir.resolve("testWriteRead.txt");
        String content = "Hello, FileUtil!\nThis is a test.";

        FileUtil.writeString(testFile.toString(), content);
        assertTrue(Files.exists(testFile), "File should be created by writeString.");

        String readContent = FileUtil.readString(testFile.toString());
        assertEquals(content, readContent, "Read content should match written content.");
    }

    @Test
    void testReadStringWhenFileDoesNotExist() {
        Path nonExistentFile = tempDir.resolve("nonExistentRead.txt");
        IOException exception = assertThrows(IOException.class, () -> {
            FileUtil.readString(nonExistentFile.toString());
        }, "Reading a non-existent file should throw IOException.");
        assertTrue(exception.getMessage().contains("Error reading file"), "Exception message should indicate file not found.");
    }

    @Test
    void testWriteLinesAndReadLines() throws IOException {
        Path testFile = tempDir.resolve("testWriteReadLines.txt");
        List<String> lines = Arrays.asList("First line", "Second line", "Third line with special chars: !@#$%^&*()");

        FileUtil.writeLines(testFile.toString(), lines);
        assertTrue(Files.exists(testFile), "File should be created by writeLines.");

        List<String> readLines = FileUtil.readLines(testFile.toString());
        assertEquals(lines, readLines, "Read lines should match written lines.");
    }

    @Test
    void testReadLinesWhenFileDoesNotExist() {
        Path nonExistentFile = tempDir.resolve("nonExistentReadLines.txt");
        IOException exception = assertThrows(IOException.class, () -> {
            FileUtil.readLines(nonExistentFile.toString());
        }, "Reading lines from a non-existent file should throw IOException.");
        assertTrue(exception.getMessage().contains("Error reading file"), "Exception message should indicate file not found.");
    }

    @Test
    void testAppendString() throws IOException {
        Path testFile = tempDir.resolve("testAppend.txt");
        String initialContent = "Initial part. ";
        String appendedContent = "Appended part.";
        String expectedContent = initialContent + appendedContent;

        FileUtil.writeString(testFile.toString(), initialContent); // Create and write initial
        FileUtil.appendString(testFile.toString(), appendedContent); // Append

        String readContent = FileUtil.readString(testFile.toString());
        assertEquals(expectedContent, readContent, "Content after append should be correct.");

        // Test appending to a non-existent file (should create it)
        Path newAppendFile = tempDir.resolve("newAppend.txt");
        FileUtil.appendString(newAppendFile.toString(), appendedContent);
        assertTrue(Files.exists(newAppendFile), "File should be created by appendString if it doesn't exist.");
        assertEquals(appendedContent, FileUtil.readString(newAppendFile.toString()));
    }

    @Test
    void testCreateDirectories() throws IOException {
        Path newDirs = tempDir.resolve("dir1/subdir2/subsubdir3");
        assertFalse(Files.exists(newDirs), "Directory structure should not exist initially.");

        FileUtil.createDirectories(newDirs.toString());
        assertTrue(Files.exists(newDirs), "Directory structure should be created.");
        assertTrue(Files.isDirectory(newDirs), "Path created should be a directory.");
    }

    @Test
    void testFileExists() throws IOException {
        Path existingFile = tempDir.resolve("existing.txt");
        Files.createFile(existingFile); // Create a file for testing

        assertTrue(FileUtil.fileExists(existingFile.toString()), "fileExists should return true for an existing file.");

        Path nonExistingFile = tempDir.resolve("nonExisting.txt");
        assertFalse(FileUtil.fileExists(nonExistingFile.toString()), "fileExists should return false for a non-existing file.");
    }
}