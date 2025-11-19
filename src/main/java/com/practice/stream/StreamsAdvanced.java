package com.practice.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAdvanced {

  static class Employee {
    String name;
    String department;
    int salary;
    int yearsExperience;

    public Employee(String name, String department, int salary, int yearsExperience) {
      this.name = name;
      this.department = department;
      this.salary = salary;
      this.yearsExperience = yearsExperience;
    }

    public String getName() {
      return name;
    }

    public String getDepartment() {
      return department;
    }

    public int getSalary() {
      return salary;
    }

    public int getYearsExperience() {
      return yearsExperience;
    }

    @Override
    public String toString() {
      return String.format("%s (%s): $%d, %d yrs", name, department, salary, yearsExperience);
    }
  }

  static class Sale {
    String product;
    String region;
    double amount;
    int quantity;

    public Sale(String product, String region, double amount, int quantity) {
      this.product = product;
      this.region = region;
      this.amount = amount;
      this.quantity = quantity;
    }

    public String getProduct() {
      return product;
    }

    public String getRegion() {
      return region;
    }

    public double getAmount() {
      return amount;
    }

    public int getQuantity() {
      return quantity;
    }
  }

  public static void main(String[] args) {
    List<Employee> employees = Arrays.asList(new Employee("Alice", "Engineering", 95000, 5),
        new Employee("Bob", "Engineering", 85000, 3), new Employee("Charlie", "Sales", 70000, 7),
        new Employee("David", "Sales", 65000, 2), new Employee("Eve", "Engineering", 105000, 8),
        new Employee("Frank", "HR", 60000, 4), new Employee("Grace", "HR", 62000, 3));

    List<Sale> sales = Arrays.asList(new Sale("Laptop", "North", 1200.0, 5),
        new Sale("Mouse", "North", 25.0, 50), new Sale("Laptop", "South", 1200.0, 3),
        new Sale("Keyboard", "North", 75.0, 20), new Sale("Mouse", "South", 25.0, 30),
        new Sale("Laptop", "East", 1200.0, 7), new Sale("Monitor", "North", 300.0, 10));

    // ========== SECTION 1: COLLECTING_AND_THEN (8 exercises) ==========

    // TODO 1.1: Get all employee names as an UNMODIFIABLE list
    // Hint: collect(Collectors.collectingAndThen(Collectors.toList(),
    // Collections::unmodifiableList))
    List<String> employeeNames = null;

    // TODO 1.2: Group employees by department, then get the SIZE of the map (how many departments)
    // Hint: collectingAndThen(groupingBy(...), Map::size)
    int departmentCount = 0;

    // TODO 1.3: Find the employee with max salary, then get their name (or "NONE" if empty)
    // Hint: collectingAndThen(maxBy(...), opt -> opt.map(Employee::getName).orElse("NONE"))
    String highestPaidName = null;

    // TODO 1.4: Get all departments as a sorted unmodifiable list
    // Hint: Collect to set (distinct), then collectingAndThen to convert to sorted list
    List<String> sortedDepartments = null;

    // TODO 1.5: Group employees by department, then count how many departments have > 2 employees
    // Hint: groupingBy -> counting -> collectingAndThen to filter the map
    long largeDeptsCount = 0;

    // TODO 1.6: Calculate average salary, then format as currency string "$XX,XXX"
    // Hint: collectingAndThen(averagingInt(...), avg -> String.format("$%,.0f", avg))
    String avgSalaryFormatted = null;

    // TODO 1.7: Get Engineering employees as a comma-separated string of names
    // Hint: filter -> map to names -> collectingAndThen(joining(", "), s -> "Engineers: " + s)
    String engineersList = null;

    // TODO 1.8: Find min salary employee, get their full info string or "No employees"
    // Hint: collectingAndThen(minBy(...), opt -> opt.map(Employee::toString).orElse("No
    // employees"))
    String lowestPaidInfo = null;

    // ========== SECTION 2: TEEING (6 exercises) ==========

    // TODO 2.1: Calculate BOTH sum and count of all salaries in one pass, return as "Sum: X, Count:
    // Y"
    // Hint: collect(Collectors.teeing(summingInt(...), counting(), (sum, count) ->
    // String.format(...)))
    String salaryStats = null;

    // TODO 2.2: Find BOTH min and max salary, return as a Pair or formatted string "Min: X, Max: Y"
    // Hint: teeing(minBy(...), maxBy(...), (min, max) -> ...)
    String salaryRange = null;

    // TODO 2.3: For Sales department only: get both average salary AND list of all names
    // Return as Map with keys "average" and "names"
    // Hint: filter first, then teeing(averagingInt(...), mapping(..., toList()), (avg, names) ->
    // Map.of(...))
    Map<String, Object> salesDeptStats = null;

    // TODO 2.4: Calculate total sales amount AND total quantity sold (across all sales)
    // Return as formatted string "Total: $X, Units: Y"
    String salesSummary = null;

    // TODO 2.5: Get both the product with highest total revenue AND the count of distinct products
    // Hint: Group by product and sum amounts first, then tee to find max and count
    // This is complex: may need two separate operations or creative use of teeing
    String topProductAndCount = null;

    // TODO 2.6: For employees with >5 years experience: get both their average salary AND their
    // count
    // Return formatted as "Senior employees: X, Avg salary: $Y"
    String seniorStats = null;

    // ========== SECTION 3: COMPLEX MULTI-STAGE PIPELINES (8 exercises) ==========

    // TODO 3.1: Group employees by department, then for each dept get the highest salary
    // Result: Map<String, Integer>
    Map<String, Integer> maxSalaryByDept = null;

    // TODO 3.2: Group sales by region, then for each region get the list of DISTINCT products sold
    // Result: Map<String, List<String>>
    Map<String, List<String>> productsByRegion = null;

    // TODO 3.3: Find the department with the highest average salary
    // Steps: group by dept -> calculate avg for each -> find max entry
    // Return department name or "NONE"
    String highestAvgSalaryDept = null;

    // TODO 3.4: For each product, calculate total revenue (amount * quantity), return top 3
    // products
    // by revenue
    // Result: List of product names
    List<String> top3Products = null;

    // TODO 3.5: Group employees by department, but only include employees with salary > 70k
    // Then for each dept, get list of names
    // Result: Map<String, List<String>>
    Map<String, List<String>> highEarnersByDept = null;

    // TODO 3.6: Create a report: for each department, show "Dept: X (Y employees, avg: $Z)"
    // Result: List<String>
    List<String> deptReports = null;

    // TODO 3.7: Find all employees who earn more than the average salary of their department
    // This requires: 1) Calculate avg per dept, 2) Filter employees based on their dept's avg
    // Result: List<Employee>
    List<Employee> aboveAvgInDept = null;

    // TODO 3.8: Nested grouping: Group sales by region, then by product, then sum the amounts
    // Result: Map<String, Map<String, Double>>
    Map<String, Map<String, Double>> regionProductRevenue = null;

    // ========== SECTION 4: EDGE CASES AND TRICKY SCENARIOS (4 exercises) ==========

    // Empty list test data
    List<Employee> emptyEmployees = new ArrayList<>();

    // TODO 4.1: Calculate average salary from EMPTY list, return 0.0 if empty
    // Hint: Use Optional.orElse()
    double avgOfEmpty = 0.0;

    // TODO 4.2: Group empty list by department - should return empty map
    Map<String, List<Employee>> emptyGrouping = null;

    // TODO 4.3: Handle null values - some employees have null departments (add test data)
    List<Employee> employeesWithNulls = Arrays.asList(new Employee("John", null, 50000, 1),
        new Employee("Jane", "IT", 60000, 2), new Employee("Jack", null, 55000, 3));
    // Group by department, handling nulls appropriately
    // Hint: Use Optional.ofNullable() or filter out nulls first
    Map<String, List<Employee>> groupedHandlingNulls = null;

    // TODO 4.4: Find max salary, but if list is empty return Optional.empty() not crash
    Optional<Integer> maxSalaryOptional = null;

    // ========== SECTION 5: CUSTOM COLLECTORS (2 exercises) ==========

    // TODO 5.1: Create a custom collector that collects to a TreeSet (sorted, no duplicates)
    // Use Collector.of(...) to create custom collector
    // Hint: Collector.of(TreeSet::new, TreeSet::add, (left, right) -> { left.addAll(right); return
    // left; })
    Set<String> sortedUniqueDepts = null;

    // TODO 5.2: Custom collector that concatenates employee names with " | " separator
    // But adds department prefix: "Engineering: Alice | Engineering: Bob | Sales: Charlie"
    String customConcatenated = null;

    // ========== TEST YOUR SOLUTIONS ==========

    System.out.println("=== SECTION 1: COLLECTING_AND_THEN ===");
    // Uncomment after implementing
    /*
     * System.out.println("1.1 Employee names: " + employeeNames); // Try to modify (should throw
     * exception): // employeeNames.add("Test"); // Should throw UnsupportedOperationException
     * 
     * System.out.println("1.2 Department count: " + departmentCount);
     * System.out.println("1.3 Highest paid: " + highestPaidName);
     * System.out.println("1.4 Sorted departments: " + sortedDepartments);
     * System.out.println("1.5 Large departments count: " + largeDeptsCount);
     * System.out.println("1.6 Avg salary formatted: " + avgSalaryFormatted);
     * System.out.println("1.7 Engineers list: " + engineersList);
     * System.out.println("1.8 Lowest paid info: " + lowestPaidInfo);
     */

    System.out.println("\n=== SECTION 2: TEEING ===");
    // Uncomment after implementing
    /*
     * System.out.println("2.1 Salary stats: " + salaryStats);
     * System.out.println("2.2 Salary range: " + salaryRange);
     * System.out.println("2.3 Sales dept stats: " + salesDeptStats);
     * System.out.println("2.4 Sales summary: " + salesSummary);
     * System.out.println("2.5 Top product and count: " + topProductAndCount);
     * System.out.println("2.6 Senior stats: " + seniorStats);
     */

    System.out.println("\n=== SECTION 3: COMPLEX PIPELINES ===");
    // Uncomment after implementing
    /*
     * System.out.println("3.1 Max salary by dept: " + maxSalaryByDept);
     * System.out.println("3.2 Products by region: " + productsByRegion);
     * System.out.println("3.3 Highest avg salary dept: " + highestAvgSalaryDept);
     * System.out.println("3.4 Top 3 products: " + top3Products);
     * System.out.println("3.5 High earners by dept: " + highEarnersByDept);
     * System.out.println("3.6 Dept reports: " + deptReports);
     * System.out.println("3.7 Above avg in dept: " + aboveAvgInDept);
     * System.out.println("3.8 Region-Product revenue: " + regionProductRevenue);
     */

    System.out.println("\n=== SECTION 4: EDGE CASES ===");
    // Uncomment after implementing
    /*
     * System.out.println("4.1 Avg of empty: " + avgOfEmpty);
     * System.out.println("4.2 Empty grouping: " + emptyGrouping);
     * System.out.println("4.3 Grouped handling nulls: " + groupedHandlingNulls);
     * System.out.println("4.4 Max salary optional: " + maxSalaryOptional);
     */

    System.out.println("\n=== SECTION 5: CUSTOM COLLECTORS ===");
    // Uncomment after implementing
    /*
     * System.out.println("5.1 Sorted unique depts: " + sortedUniqueDepts);
     * System.out.println("5.2 Custom concatenated: " + customConcatenated);
     */
  }
}
