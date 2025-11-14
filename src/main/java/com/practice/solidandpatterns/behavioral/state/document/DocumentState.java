package com.practice.solidandpatterns.behavioral.state.document;

public interface DocumentState {
  void publish(Document document);

  void edit(Document document, String newContent);

  void archive(Document document);

  void delete(Document document);
}
