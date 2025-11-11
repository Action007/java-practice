package com.practice.solidandpatterns.behavioral.iterator;

public class MainApp {
  public static void main(String[] args) {
    CourseCatalog courseCatalog = new CourseCatalog();
    courseCatalog.addCourse(new Course("Course #1"));
    courseCatalog.addCourse(new Course("Course #1"));
    courseCatalog.addCourse(new Course("Course #1"));
    courseCatalog.addCourse(new Course("Course #1"));

    PatternIterator<Course> courseIterator = courseCatalog.iterator();

    while (courseIterator.hasNext()) {
      Course tempCourse = courseIterator.next();
      System.out.println("Course name: " + tempCourse.getName());
    }
  }
}
