package com.collections.practice.UserSessionTracker;

class UserSession {
  private final String userId;
  private final long loginTime;
  private long lastAccessed;

  public UserSession(String userId) {
    this.userId = userId;
    this.loginTime = System.currentTimeMillis();
    this.lastAccessed = this.loginTime;
  }

  public String getUserId() {
    return userId;
  }

  public long getLoginTime() {
    return loginTime;
  }

  public long getLastAccessed() {
    return lastAccessed;
  }

  public void updateAccessTime() {
    this.lastAccessed = System.currentTimeMillis();
  }

  @Override
  public String toString() {
    return "UserSession{" +
        "userId='" + userId + '\'' +
        ", loginTime=" + loginTime +
        ", lastAccessed=" + lastAccessed +
        '}';
  }
}