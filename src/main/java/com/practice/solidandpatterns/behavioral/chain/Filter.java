package com.practice.solidandpatterns.behavioral.chain;

public interface Filter {

  void setNext(Filter filter);

  void apply(Request request);
}
