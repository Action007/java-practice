package advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import com.practice.testing.samples.advanced.FileProcessor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {
  FileProcessor fileProcessor;

  @BeforeEach
  void setup() {
    fileProcessor = new FileProcessor();
  }

  @Test
  void testCreateLogFile(@TempDir Path tempDir) throws IOException {
    // Act
    String filename = fileProcessor.createLogFile(tempDir, "Startup complete");

    // Assert
    Path logFile = tempDir.resolve(filename);
    assertTrue(Files.exists(logFile), "app.log should exist in temp directory");

    // Optional: Verify content
    String content = Files.readString(logFile);
    assertEquals("Startup complete", content.trim(), "File content should match");
  }


  @Test
  @EnabledOnOs(OS.WINDOWS)
  void testWindowsSpecificFeature() {
    assertTrue(fileProcessor.isWindows(), "Should return true on Windows");
  }
}
