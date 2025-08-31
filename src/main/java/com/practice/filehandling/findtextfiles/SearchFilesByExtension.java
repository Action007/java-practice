package com.practice.filehandling.findtextfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SearchFilesByExtension {
  public static void main(String[] args) {
    Path path = Paths.get("src", "main", "java", "com", "practice", "filehandling", "configreader",
        "config.env");

    if (!Files.exists(path)) {
      System.err.println("File not found!");
      return;
    }

    SearchFilesByExtension searchFilesByExtension = new SearchFilesByExtension();
    try {
      System.out
          .println(searchFilesByExtension.getNumberOfFilesWithExtension(Paths.get("src"), "java"));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public long getNumberOfFilesWithExtension(Path pathToStartSearch, String extension)
      throws IOException {
    if (pathToStartSearch == null || !Files.exists(pathToStartSearch)
        || !Files.isDirectory(pathToStartSearch) || extension == null
        || extension.trim().isEmpty()) {
      return 0;
    }

    try (Stream<Path> stream = Files.walk(pathToStartSearch)) {
      return stream.filter(Files::isRegularFile).filter(path -> path.getFileName().toString()
          .toLowerCase().endsWith("." + extension.toLowerCase().trim())).count();
    }
  }
}
