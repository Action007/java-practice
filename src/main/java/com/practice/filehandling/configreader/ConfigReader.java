package com.practice.filehandling.configreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConfigReader {
  public static void main(String[] args) {
    Path path = Paths.get("src", "main", "java", "com", "practice", "filehandling", "configreader",
        "config.env");

    if (!Files.exists(path)) {
      System.err.println("File not found!");
      return;
    }

    ConfigReader configReader = new ConfigReader();

    try {
      String discount = configReader.getValueFromConfigMap(path, "discount");
      String user = configReader.getValueFromConfigMap(path, "database.user");
      String password = configReader.getValueFromConfigMap(path, "database.password");
      String testForeNull = configReader.getValueFromConfigMap(path, "test");

      System.err.println("discount: " + discount);
      System.err.println("user: " + user);
      System.err.println("password: " + password);
      System.err.println("null: " + testForeNull);
    } catch (IOException e) {
      System.err.println("Handling file error: " + e.getMessage());
    }
  }

  public String getValueFromConfigMap(Path configMapFilePath, String keyName) throws IOException {
    List<String> lines = Files.readAllLines(configMapFilePath);
    String value = null;

    for (String line : lines) {
      if (line == null || line.trim().isEmpty() || line.trim().startsWith("#")) {
        continue;
      }

      int indexOfEquals = line.indexOf("=");
      if (indexOfEquals == -1) {
        continue;
      }

      String key = line.substring(0, indexOfEquals).trim();
      if (keyName.equals(key)) {
        value = line.substring(indexOfEquals + 1).trim();
      }
    }

    return value;
  }
}
