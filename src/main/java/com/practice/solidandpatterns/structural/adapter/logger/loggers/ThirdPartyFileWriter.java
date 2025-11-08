package com.practice.solidandpatterns.structural.adapter.logger.loggers;

public class ThirdPartyFileWriter {
  private String filePath;

  public ThirdPartyFileWriter(String path) {
    this.filePath = path;
  }

  public void writeToFile(String content) {
    // Simulated file writing
    System.out.println("[FILE: " + filePath + "] Writing: " + content);
  }

  public void appendLine(String line, String prefix) {
    System.out.println("[FILE: " + filePath + "] " + prefix + ": " + line);
  }
}
