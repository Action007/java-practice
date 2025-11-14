package com.practice.solidandpatterns.behavioral.state.document;

public class MainApp {
  public static void main(String[] args) {
    Document document = new Document("Lets test this piece of shit");
    document.edit(document.getContent() + "!!!");
    document.publish();
    document.edit("haha");
    document.publish();
    document.archive();
    document.delete();
  }
}
