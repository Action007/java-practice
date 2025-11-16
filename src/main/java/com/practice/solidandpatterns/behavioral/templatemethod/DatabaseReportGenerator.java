package com.practice.solidandpatterns.behavioral.templatemethod;

public class DatabaseReportGenerator extends AbstractReportGenerator {
  @Override
  protected void openDataSource() {
    System.out.println("Connecting to database...");
  }

  @Override
  protected void extractData() {
    System.out.println("Executing SQL query...");
  }

  @Override
  protected void closeDataSource() {
    System.out.println("Closing database connection");
  }

  @Override
  protected void beforeProcessing() {
    System.out.println("HOOK: Validating data integrity before processing");
  }

  @Override
  protected void afterProcessing() {
    System.out.println("HOOK: Caching processed results");
  }
}
