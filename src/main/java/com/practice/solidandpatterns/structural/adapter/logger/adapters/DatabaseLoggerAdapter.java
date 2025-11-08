package com.practice.solidandpatterns.structural.adapter.logger.adapters;

import java.time.LocalDateTime;
import com.practice.solidandpatterns.structural.adapter.logger.loggers.ExternalDatabaseLogger;

public class DatabaseLoggerAdapter implements LoggerAdapter {
  ExternalDatabaseLogger externalDatabaseLogger;

  public DatabaseLoggerAdapter(ExternalDatabaseLogger externalDatabaseLogger) {
    this.externalDatabaseLogger = externalDatabaseLogger;
  }

  @Override
  public void log(String message, String level) {
    LocalDateTime now = LocalDateTime.now();
    externalDatabaseLogger.saveLogEntry(message, 1, now);
  }

}
