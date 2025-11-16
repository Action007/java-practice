package com.practice.solidandpatterns.behavioral.templatemethod;

public class CSVReportGenerator extends AbstractReportGenerator {
  private String fileName;

  public CSVReportGenerator(String fileName) {
    this.fileName = fileName;
  }

  @Override
  protected void openDataSource() {
    System.out.println("Opening CSV file: " + fileName);
    System.out.println("Initializing CSV parser...");
  }

  @Override
  protected void extractData() {
    System.out.println("Extracting data from CSV...");
    System.out.println("Reading row 1: John,Doe,30");
    System.out.println("Reading row 2: Jane,Smith,25");
    System.out.println("Extracted 2 records");
  }

  @Override
  protected void closeDataSource() {
    System.out.println("Closing CSV file: " + fileName);
    System.out.println("Releasing file handle");
  }
}
