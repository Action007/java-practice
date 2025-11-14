package com.practice.solidandpatterns.behavioral.state.document;

public class PublishedState implements DocumentState {

  @Override
  public void publish(Document document) {
    System.out.println("Already published.");
  }

  @Override
  public void edit(Document document, String newContext) {
    System.out.println("Published documents are read-only. Unpublish or archive first.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Document moving to archiving state");
    document.setState(new ArchivedState());
  }

  @Override
  public void delete(Document document) {
    System.out.println("Published documents cannot be deleted — archive instead.");
  }
}
