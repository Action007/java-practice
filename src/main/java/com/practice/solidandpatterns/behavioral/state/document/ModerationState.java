package com.practice.solidandpatterns.behavioral.state.document;

public class ModerationState implements DocumentState {

  @Override
  public void publish(Document document) {
    System.out.println("Approved and published!");
    document.setState(new PublishedState());
  }

  @Override
  public void edit(Document document, String newContext) {
    System.out.println("Document is under review — editing is locked.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Cannot archive while in moderation.");
  }

  @Override
  public void delete(Document document) {
    System.out.println("Deletion is disabled during moderation.");
  }

}
