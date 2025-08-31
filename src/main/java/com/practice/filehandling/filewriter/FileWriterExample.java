package com.practice.filehandling.filewriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileWriterExample {
  public static void main(String[] args) {
    FileWriterExample fileWriter = new FileWriterExample();
    fileWriter.writeFromFile();
  }

  public void writeFromFile() {
    Path path = Paths.get("src", "main", "java", "com", "practice", "filehandling", "filewriter",
        "text.txt");

    // Help debug: show where it's looking
    System.out.println("Looking for file: " + path.toAbsolutePath());

    try {
      List<String> text = Arrays.asList("I'm testing this java code.",
          "This is first time im writing something to file in java.");

      Files.write(path, text);

      System.out.println("Successfully wrote to file: " + path.toAbsolutePath());
      System.out.println("Content:");

      Files.readAllLines(path).forEach(System.out::println);
    } catch (IOException e) {
      System.err.println("Error writing file:" + e.getMessage());
    }
  }
}
