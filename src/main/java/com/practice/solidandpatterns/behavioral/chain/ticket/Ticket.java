package com.practice.solidandpatterns.behavioral.chain.ticket;

public class Ticket {
  private String title;
  private String description;
  private PriorityType priority;
  private ComplexityType complexity;

  public Ticket(String title, String description, PriorityType priority,
      ComplexityType complexity) {
    this.title = title;
    this.description = description;
    this.priority = priority;
    this.complexity = complexity;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PriorityType getPriority() {
    return this.priority;
  }

  public void setPriority(PriorityType priority) {
    this.priority = priority;
  }

  public ComplexityType getComplexity() {
    return this.complexity;
  }

  public void setComplexity(ComplexityType complexity) {
    this.complexity = complexity;
  }

  @Override
  public String toString() {
    return "{" + " title='" + getTitle() + "'" + ", description='" + getDescription() + "'"
        + ", priority='" + getPriority() + "'" + ", complexity='" + getComplexity() + "'" + "}";
  }

}
