package com.practice.solidandpatterns.behavioral.chain.filter;

public interface Filter {

  void setNext(Filter filter);

  void apply(Request request);
}
