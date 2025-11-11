package com.practice.solidandpatterns.structural.composite.orgstructure;

public interface OrganizationalUnit {
  String getName();

  long getSalary(); // For leaves: actual salary; for composites: total

  int getEmployeeCount(); // Leaf = 1, Composite = sum

  void printStructure(String indent);

  // Composite ops (leaves will throw exception)
  default void add(OrganizationalUnit unit) {
    throw new UnsupportedOperationException("Cannot add to leaf");
  }

  default void remove(OrganizationalUnit unit) {
    throw new UnsupportedOperationException("Cannot remove from leaf");
  }
}
