package com.practice.solidandpatterns.behavioral.templatemethod;

public class JSONReportGenerator extends AbstractReportGenerator {
  private String apiUrl;

  public JSONReportGenerator(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  @Override
  protected void openDataSource() {
    System.out.println("Connecting to API: " + apiUrl);
    System.out.println("Authenticating with token...");
    System.out.println("Connection established");
  }

  @Override
  protected void extractData() {
    System.out.println("Fetching JSON data from API...");
    System.out.println("Parsing JSON response...");
    System.out.println("Found 5 records in JSON array");
  }

  @Override
  protected void closeDataSource() {
    System.out.println("Closing API connection: " + apiUrl);
    System.out.println("Logging out...");
  }

}
