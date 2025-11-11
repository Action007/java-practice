package com.practice.solidandpatterns.structural.composite.filesystem;

public class File implements FileSystemComponent {

  private String name;
  private long size;

  public File(String name, long size) {
    this.name = name;
    this.size = size;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getSize() {
    return size;
  }

  @Override
  public void print(String indent) {
    System.out.println(indent + "- " + name + " (" + size + " bytes)");
  }

}
