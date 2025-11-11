package com.practice.solidandpatterns.structural.composite.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
  private String name;
  private List<FileSystemComponent> components = new ArrayList<>();

  public Folder(String name) {
    this.name = name;
  }

  public void addComponent(FileSystemComponent component) {
    components.add(component);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getSize() {
    return components.stream().mapToLong(FileSystemComponent::getSize).sum();
  }

  @Override
  public void print(String indent) {
    System.out.println(indent + "+ " + getName() + " (" + getSize() + " bytes)");

    // recursive with extra indentation
    components.stream().forEach((component) -> component.print(indent + " "));
  }
}
