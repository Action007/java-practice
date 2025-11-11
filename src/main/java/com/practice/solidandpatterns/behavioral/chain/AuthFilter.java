package com.practice.solidandpatterns.behavioral.chain;

public class AuthFilter implements Filter {

  private Filter nextFilter;

  @Override
  public void setNext(Filter filter) {
    this.nextFilter = filter;
  }

  @Override
  public void apply(Request request) {
    System.out.println("AuthFilter: checking authentication for " + request.getPayload());

    if (authenticate(request)) {
      request.setAttributes("authenticated", true);
      System.out.println("AuthFilter: authentication successfully");
    } else {
      request.setAttributes("authenticated", false);
      System.out.println("AuthFilter: authentication failed - stopping chain");
      return;
    }

    if (nextFilter != null) {
      nextFilter.apply(request);
    }
  }

  private boolean authenticate(Request request) {
    String payload = request.getPayload();
    return payload.contains("auth");
  }
}
