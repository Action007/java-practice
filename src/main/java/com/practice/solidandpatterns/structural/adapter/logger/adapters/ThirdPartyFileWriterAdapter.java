package com.practice.solidandpatterns.structural.adapter.logger.adapters;

import com.practice.solidandpatterns.structural.adapter.logger.loggers.ThirdPartyFileWriter;

public class ThirdPartyFileWriterAdapter implements LoggerAdapter {
  ThirdPartyFileWriter thirdPartyFileWriter;

  public ThirdPartyFileWriterAdapter(ThirdPartyFileWriter thirdPartyFileWriter) {
    this.thirdPartyFileWriter = thirdPartyFileWriter;
  }

  @Override
  public void log(String message, String level) {
    thirdPartyFileWriter.writeToFile("[" + level + "] " + message);
  }

}
