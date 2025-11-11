package com.practice.solidandpatterns.structural.composite.filesystem;


// Component role in Composite pattern.

// Common interface for both leaves (Files) and composites (Folder),
// letting client treat them uniformly
public interface FileSystemComponent {
  String getName();

  long getSize();

  void print(String indent);
}
