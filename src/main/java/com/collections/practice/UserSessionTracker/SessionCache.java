package com.collections.practice.UserSessionTracker;

import java.util.LinkedHashMap;

class SessionCache extends LinkedHashMap<String, UserSession> {
  private static final int MAX_SIZE = 100;

  // Constructor: You must call super() with correct arguments
  public SessionCache() {
    super(128, 0.75f, true);
  }

  @Override
  protected boolean removeEldestEntry(java.util.Map.Entry<String, UserSession> eldest) {
    return size() > MAX_SIZE;
  }
}