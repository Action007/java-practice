package com.practice.solidandpatterns.structural.adapter.logger.loggers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExternalDatabaseLogger {
  private String connectionString;

  public ExternalDatabaseLogger(String connStr) {
    this.connectionString = connStr;
  }

  public void insertRecord(String table, Map<String, String> data) {
    System.out.print("[DB: " + connectionString + "] INSERT INTO " + table + " - ");
    for (Map.Entry<String, String> entry : data.entrySet()) {
      System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
    }
    System.out.println();
  }

  public boolean saveLogEntry(String message, int severityCode, LocalDateTime timestamp) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println("[DB: " + connectionString + "] SAVE - Severity:" + severityCode + " Time:"
        + timestamp.format(formatter) + " Msg:" + message);
    return true;
  }
}
