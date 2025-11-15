package com.practice.solidandpatterns.structural.proxy.imagehandler;

public class ProxyImage implements Image {
  public String fileName;

  public RealImage realImage;

  public ProxyImage(String fileName) {
    this.fileName = fileName;
    System.out.println("ProxyImage created for " + fileName + " (not loaded yet)");
  }

  @Override
  public void display() {
    if (realImage == null) {
      System.out.println("First time displaying, loading now...");
      realImage = new RealImage(fileName);
    }
    realImage.display();
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
