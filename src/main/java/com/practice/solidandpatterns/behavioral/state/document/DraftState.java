package com.practice.solidandpatterns.behavioral.state.document;

public class DraftState implements DocumentState {

  @Override
  public void publish(Document document) {
    System.out.println("Sending to moderation...");
    document.setState(new ModerationState());
  }

  @Override
  public void edit(Document document, String newContext) {
    document.setContent(newContext);
  }

  @Override
  public void archive(Document document) {
    System.out.println("You can't archive a draft. Publish it first.");
  }

  @Override
  public void delete(Document document) {
    System.out.println("Document removed.");
    document.setContent(null);
  }
}
