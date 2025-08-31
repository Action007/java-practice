package com.practice.filehandling.filereader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class FileReaderExample {
  public static void main(String[] args) {
    FileReaderExample fileReader = new FileReaderExample();
    fileReader.readFromFile();
  }

  public void readFromFile() {
    Path path = Paths.get("src", "main", "java", "com", "practice", "filehandling", "filereader",
        "text.txt");

    // Help debug: show where it's looking
    System.out.println("Looking for file: " + path.toAbsolutePath());

    if (!Files.exists(path)) {
      System.err.println("File not found!");
      return;
    }

    try {
      List<String> text = Files.readAllLines(path, StandardCharsets.UTF_8);
      IntStream.range(0, text.size())
          .forEach((i) -> System.out.println((i + 1) + ". " + text.get(i)));
    } catch (IOException e) {
      System.err.println("Error reading file:" + e.getMessage());
    }
  }
}
