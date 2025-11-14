package com.practice.solidandpatterns.behavioral.state.document;

public class ArchivedState implements DocumentState {

  @Override
  public void publish(Document document) {
    System.out.println("Archived documents cannot be republished directly.");
  }

  @Override
  public void edit(Document document, String newContext) {
    System.out.println("Archived content is frozen.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Already archived.");
  }

  @Override
  public void delete(Document document) {
    System.out.println("Document removed.");
    document.setContent(null);
  }

}

