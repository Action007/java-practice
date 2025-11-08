package com.practice.solidandpatterns.structural.adapter.logger.loggers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LegacyConsoleWriter {
  public void printMessage(String text) {
    System.out.println("[CONSOLE] " + text);
  }

  public void printWithTimestamp(String text, LocalDateTime time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    System.out.println("[CONSOLE " + time.format(formatter) + "] " + text);
  }
}
