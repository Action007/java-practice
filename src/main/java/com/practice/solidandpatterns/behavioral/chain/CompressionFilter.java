package com.practice.solidandpatterns.behavioral.chain;

public class CompressionFilter implements Filter {
  private Filter nextFilter;

  @Override
  public void setNext(Filter filter) {
    this.nextFilter = filter;
  }

  @Override
  public void apply(Request request) {
    System.out.println("CompressionFilter: compressing request " + request.getPayload());

    request.setAttributes("compressed", true);

    if (nextFilter != null) {
      nextFilter.apply(request);
    }
  }
}
