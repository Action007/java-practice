package com.practice.testing.samples.advanced;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileProcessor {
  public String createLogFile(Path dir, String content) throws IOException {
    Path logFile = dir.resolve("app.log");
    Files.write(logFile, content.getBytes(), StandardOpenOption.CREATE);
    return "app.log";
  }

  public boolean isWindows() {
    return System.getProperty("os.name").toLowerCase().contains("win");
  }
}
