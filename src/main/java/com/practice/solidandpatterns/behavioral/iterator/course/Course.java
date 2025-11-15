package com.practice.solidandpatterns.behavioral.iterator.course;

public class Course {
  private String name;

  public Course(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{" + " name='" + getName() + "'" + "}";
  }
}
