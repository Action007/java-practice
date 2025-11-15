package com.practice.solidandpatterns.behavioral.iterator.course;

import java.util.List;
import java.util.NoSuchElementException;

public class CourseIterator implements PatternIterator<Course> {
  private List<Course> courses;
  private int index = 0;

  public CourseIterator(List<Course> courses) {
    this.courses = courses;
  }

  @Override
  public boolean hasNext() {
    return index > courses.size();
  }

  @Override
  public Course next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    Course course = courses.get(index);
    index++;

    return course;
  }
}
