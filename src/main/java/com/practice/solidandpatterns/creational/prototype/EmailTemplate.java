package com.practice.solidandpatterns.creational.prototype;

public class EmailTemplate implements Cloneable {
  private String subject;
  private String body;
  private String footer;

  public EmailTemplate(String subject, String body, String footer) {
    this.subject = subject;
    this.body = body;
    this.footer = footer;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setFooter(String footer) {
    this.footer = footer;
  }

  public void setBody(String body) {
    this.body = body;
  }


  public void sendEmail(String to) {
    System.out.println("Sending email to: " + to);
    System.out.println("Subject: " + subject);
    System.out.println("Body: " + body);
    System.out.println("Footer: " + footer);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return (EmailTemplate) super.clone();
  }

  @Override
  public String toString() {
    return "EmailTemplate(" + "subject='" + subject + '\'' + ", body='" + body + '\'' + ", footer='"
        + footer + '\'' + ')';
  }
}
