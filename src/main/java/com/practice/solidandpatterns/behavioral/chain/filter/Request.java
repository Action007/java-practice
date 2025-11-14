package com.practice.solidandpatterns.behavioral.chain.filter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Request {

  private String payload;
  Map<String, Object> attributes = new LinkedHashMap<>();

  public Request(String payload) {
    this.payload = payload;
  }

  public String getPayload() {
    return payload;
  }

  public void setAttributes(String key, Object value) {
    attributes.put(key, value);
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  @Override
  public String toString() {
    return "{" + " payload='" + getPayload() + "'" + ", attributes='" + attributes + "'" + "}";
  }


}
