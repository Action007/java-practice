package com.practice.solidandpatterns.behavioral.iterator;

public interface PatternIterator<T> {
  boolean hasNext();

  T next();
}
