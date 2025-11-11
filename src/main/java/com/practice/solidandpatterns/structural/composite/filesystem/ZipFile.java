package com.practice.solidandpatterns.structural.composite.filesystem;

public class ZipFile implements FileSystemComponent {
  private String name;
  private long originalSize;
  private double compressionRatio;

  // precomputed
  private long compressedSize;

  public ZipFile(String name, long originalSize, double compressionRatio) {
    this.name = name;
    this.originalSize = originalSize;
    this.compressionRatio = compressionRatio;

    this.compressedSize = Math.round(originalSize * compressionRatio);
  }

  @Override
  public String getName() {
    return getName();
  }

  @Override
  public long getSize() {
    return compressedSize;
  }

  @Override
  public void print(String indent) {
    System.out.println(indent + "- " + name + " (" + compressedSize + " bytes)");
  }
}
