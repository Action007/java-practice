package com.practice.solidandpatterns.behavioral.state.document;

public class Document {
  private String content;
  private DocumentState currentState;

  public Document(String content) {
    this.content = content;
    this.currentState = new DraftState();
  }

  public String getContent() {
    return this.content;
  }

  protected void setState(DocumentState state) {
    System.out.println("Current state: " + state.getClass().getSimpleName());
    this.currentState = state;
  }

  protected void setContent(String newContent) {
    this.content = newContent;
  }

  public void publish() {
    currentState.publish(this);
  }

  public void edit(String newContent) {
    currentState.edit(this, newContent);
  }

  public void archive() {
    currentState.archive(this);
  }

  public void delete() {
    currentState.delete(this);
  }

  @Override
  public String toString() {
    return "{" + " content='" + getContent() + "'" + "}";
  }
}
