package com.practice.solidandpatterns.creational.prototype;

public class MainApp {
  public static void main(String[] args) throws CloneNotSupportedException {
    EmailTemplate baseTemplate =
        new EmailTemplate("Hello!", "This is your monthly update", "Regar XYZ");
    System.out.println("Base Template: " + baseTemplate);

    System.out.println();

    System.out.println("SENDING GROUP EMAIL");

    EmailTemplate groupEmail = (EmailTemplate) baseTemplate.clone();
    groupEmail.setSubject("Hello Team!");
    groupEmail.sendEmail("techteam@gmail.com");

    System.out.println();

    System.out.println("SENDING INDIVIDUAL EMAIL");

    EmailTemplate individualEmail = (EmailTemplate) baseTemplate.clone();
    individualEmail.setSubject("Hello Chad");
    individualEmail.sendEmail("chad@gmail.com");
  }
}
