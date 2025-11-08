package com.practice.solidandpatterns.structural.adapter.logger.adapters;

import java.time.LocalDateTime;
import com.practice.solidandpatterns.structural.adapter.logger.loggers.LegacyConsoleWriter;

public class ConsoleWriterAdapter implements LoggerAdapter {
  LegacyConsoleWriter legacyConsoleWriter;

  public ConsoleWriterAdapter(LegacyConsoleWriter legacyConsoleWriter) {
    this.legacyConsoleWriter = legacyConsoleWriter;
  }

  @Override
  public void log(String message, String level) {
    LocalDateTime now = LocalDateTime.now();
    legacyConsoleWriter.printWithTimestamp("[" + level + "] " + message, now);
  }

}
