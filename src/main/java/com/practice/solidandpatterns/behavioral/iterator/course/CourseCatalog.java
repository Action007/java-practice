package com.practice.solidandpatterns.behavioral.iterator.course;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
  private List<Course> courses = new ArrayList<>();

  public void addCourse(Course course) {
    courses.add(course);
  }

  public PatternIterator<Course> iterator() {
    return new CourseIterator(courses);
  }
}
