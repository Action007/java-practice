package com.practice.solidandpatterns.behavioral.templatemethod;

public class MainApp {
  public static void main(String[] args) {
    AbstractReportGenerator csvReport = new CSVReportGenerator("employees.csv");
    csvReport.generateReport();

    AbstractReportGenerator jsonReport = new JSONReportGenerator("https://api.example.com/data");
    jsonReport.generateReport();

    AbstractReportGenerator databaseReport = new DatabaseReportGenerator();
    databaseReport.generateReport();
  }
}
