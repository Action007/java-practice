package com.practice.solidandpatterns.structural.proxy.imagehandler;

public record MainApp() {
  public static void main(String[] args) {
    System.out.println("=== Using RealImage Directly ===");
    Image realImage = new RealImage("image.png");
    realImage.display();

    System.out.println();

    System.out.println("=== Using ProxyImage ===");
    Image proxyImage = new ProxyImage("image.jpg");
    proxyImage.display();
  }
}
