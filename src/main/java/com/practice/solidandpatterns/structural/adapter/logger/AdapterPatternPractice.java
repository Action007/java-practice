package com.practice.solidandpatterns.structural.adapter.logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.practice.solidandpatterns.structural.adapter.logger.adapters.ConsoleWriterAdapter;
import com.practice.solidandpatterns.structural.adapter.logger.adapters.DatabaseLoggerAdapter;
import com.practice.solidandpatterns.structural.adapter.logger.adapters.LoggerAdapter;
import com.practice.solidandpatterns.structural.adapter.logger.adapters.ThirdPartyFileWriterAdapter;
import com.practice.solidandpatterns.structural.adapter.logger.loggers.ExternalDatabaseLogger;
import com.practice.solidandpatterns.structural.adapter.logger.loggers.LegacyConsoleWriter;
import com.practice.solidandpatterns.structural.adapter.logger.loggers.ThirdPartyFileWriter;

public class AdapterPatternPractice {
  public static void main(String[] args) {
    System.out.println("=== Adapter Pattern Practice ===\n");


    ExternalDatabaseLogger externalDatabaseLogger = new ExternalDatabaseLogger("test.com");
    LegacyConsoleWriter legacyConsoleWriter = new LegacyConsoleWriter();
    ThirdPartyFileWriter thirdPartyFileWriter = new ThirdPartyFileWriter("/src/test");

    List<LoggerAdapter> adapters = new ArrayList<>();
    adapters.add(new ConsoleWriterAdapter(legacyConsoleWriter));
    adapters.add(new DatabaseLoggerAdapter(externalDatabaseLogger));
    adapters.add(new ThirdPartyFileWriterAdapter(thirdPartyFileWriter));

    System.out.println("\n=== Practice Complete ===");
  }
}
