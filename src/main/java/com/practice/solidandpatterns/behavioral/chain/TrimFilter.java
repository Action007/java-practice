package com.practice.solidandpatterns.behavioral.chain;

public class TrimFilter implements Filter {

  private Filter nextFilter;

  @Override
  public void setNext(Filter filter) {
    this.nextFilter = filter;
  }

  @Override
  public void apply(Request request) {
    System.out.println("TrimFilter: trimming request " + request.getPayload());

    String trimPayload = request.getPayload().trim();
    request.setPayload(trimPayload);
    System.out.println("TrimFilter: AFTER trimming request " + request.getPayload());

    request.setAttributes("trimmed", true);

    if (nextFilter != null) {
      nextFilter.apply(request);
    }
  }

}
