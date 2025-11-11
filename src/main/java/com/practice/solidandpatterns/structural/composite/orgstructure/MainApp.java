package com.practice.solidandpatterns.structural.composite.orgstructure;

public class MainApp {
  public static void main(String[] args) {
    Employee employee1 = new Employee("John", 100);
    Employee employee2 = new Employee("John", 100);
    Employee employee3 = new Employee("John", 100);
    Employee employee4 = new Employee("John", 100);
    Employee employee5 = new Employee("John", 100);

    OrganizationalUnit department = new Department("Department - 1");
    department.add(employee1);
    department.add(employee2);
    department.add(employee3);
    department.add(employee4);
    department.add(employee5);

    OrganizationalUnit department2 = new Department("Department - 2");
    department2.add(employee1);
    department2.add(employee2);
    department2.add(employee3);
    department2.add(employee4);
    department2.add(employee5);
    department2.add(department);


    OrganizationalUnit department3 = new Department("Department - 3");
    department3.add(employee1);
    department3.add(employee2);
    department3.add(employee3);
    department3.add(employee4);
    department3.add(employee5);
    department3.add(department);
    department3.add(department2);


    department3.printStructure("");
    System.out.println(department3.getEmployeeCount());;
    System.out.println(department3.getSalary());
  }
}
