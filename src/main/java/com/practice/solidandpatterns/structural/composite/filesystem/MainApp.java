package com.practice.solidandpatterns.structural.composite.filesystem;

public class MainApp {
  public static void main(String[] args) {
    File file1 = new File("Dadash1", 1000);
    File file2 = new File("Dadash2", 2000);
    File file3 = new File("Dadash3", 3000);
    File file4 = new File("Dadash4", 4000);
    File file5 = new File("Dadash5", 5000);
    File file6 = new File("Dadash6", 6000);
    File file7 = new File("Dadash7", 7000);

    Folder folder1 = new Folder("Folder1");
    Folder folder2 = new Folder("Folder2");

    folder1.addComponent(file1);
    folder1.addComponent(file2);
    folder1.addComponent(file3);
    folder1.addComponent(file4);
    folder1.addComponent(file5);
    folder1.addComponent(file6);
    folder1.addComponent(file7);

    folder1.addComponent(file1);
    folder2.addComponent(file2);
    folder2.addComponent(file3);
    folder2.addComponent(file4);
    folder2.addComponent(file5);
    folder2.addComponent(file6);
    folder2.addComponent(file7);

    folder1.addComponent(folder2);

    folder1.addComponent(new ZipFile("archive.zim", 6000, 0.4));

    long size = folder1.getSize();
    System.out.println(size);

    System.out.println();

    folder1.print("");
  }
}
