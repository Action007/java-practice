package com.practice.solidandpatterns.structural.proxy.imagehandler;

public class RealImage implements Image {
  private String fileName;

  public RealImage(String fileName) {
    this.fileName = fileName;
    loadImageFromDisk();
  }

  private void loadImageFromDisk() {
    System.out.println("Loading image from disk: " + fileName);
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
    }

    System.out.println("Loaded: " + fileName);
  }

  @Override
  public void display() {
    System.out.println("Displaying: " + fileName);
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
