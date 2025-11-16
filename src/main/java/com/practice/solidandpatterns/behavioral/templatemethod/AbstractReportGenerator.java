package com.practice.solidandpatterns.behavioral.templatemethod;

public abstract class AbstractReportGenerator {
  public final void generateReport() {
    System.out.println("=== Starting Report Generation ===");
    openDataSource();
    extractData();
    beforeProcessing();
    processData();
    afterProcessing();
    formatOutput();
    closeDataSource();
    System.out.println("=== Report Generation Complete ===");
  }

  protected void processData() {
    System.out.println("Processing data: Applying business rules...");
    System.out.println("- Filtering invalid records");
    System.out.println("- Calculating totals");
    System.out.println("- Sorting by date");
  }

  protected void formatOutput() {
    System.out.println("Formatting output: Creating report structure...");
    System.out.println("- Adding headers");
    System.out.println("- Creating summary section");
    System.out.println("- Generating footer");
  }

  protected void beforeProcessing() {}

  protected void afterProcessing() {}

  protected abstract void openDataSource();

  protected abstract void extractData();

  protected abstract void closeDataSource();
}
