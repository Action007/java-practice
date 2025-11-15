package com.practice.solidandpatterns.behavioral.iterator.course;

public interface PatternIterator<T> {
  boolean hasNext();

  T next();
}
