package com.practice.solidandpatterns.behavioral.chain;

public class LogFilter implements Filter {
  private Filter nextFilter;

  @Override
  public void setNext(Filter filter) {
    this.nextFilter = filter;
  }

  @Override
  public void apply(Request request) {
    System.out.println("LogFilter: logging request " + request.getPayload());

    request.setAttributes("logged", true);

    if (nextFilter != null) {
      nextFilter.apply(request);
    }
  }
}
