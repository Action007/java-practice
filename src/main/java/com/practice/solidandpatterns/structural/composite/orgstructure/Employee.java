package com.practice.solidandpatterns.structural.composite.orgstructure;

public class Employee implements OrganizationalUnit {
  private String name;
  private long salary;

  public Employee(String name, long salary) {
    this.name = name;
    this.salary = salary;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "{" + " name='" + getName() + "'" + ", salary='" + getSalary() + "'" + "}";
  }

  @Override
  public long getSalary() {
    return salary;
  }

  @Override
  public int getEmployeeCount() {
    return 1;
  }

  @Override
  public void printStructure(String indent) {
    System.out.println(indent + "- " + name + " ($" + salary + ")");
  }
}
