package com.practice.solidandpatterns.structural.composite.orgstructure;

import java.util.ArrayList;
import java.util.List;

public class Department implements OrganizationalUnit {
  private String name;
  private final List<OrganizationalUnit> members = new ArrayList<>();

  public Department(String name) {
    this.name = name;
  }

  public void add(OrganizationalUnit unit) {
    members.add(unit);
  }

  @Override
  public long getSalary() {
    return members.stream().mapToLong(OrganizationalUnit::getSalary).sum();
  }

  @Override
  public int getEmployeeCount() {
    return members.stream().mapToInt(OrganizationalUnit::getEmployeeCount).sum();
  }

  @Override
  public void printStructure(String indent) {
    System.out.println(indent + "+ " + name + " [Total: $" + getSalary() + "]");
    members.forEach(m -> m.printStructure(indent + "  "));
  }

  @Override
  public String getName() {
    return name;
  }
}
