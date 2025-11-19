package com.practice.stream;

import java.util.*;
import java.util.stream.Collector;
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

    // ========== SECTION 1: COLLECTING_AND_THEN ==========
    // WHY: collectingAndThen() transforms the RESULT of a collection operation.
    // Think of it as: collect -> then apply a final transformation.
    // Use cases: making collections immutable, extracting metadata (size, empty check),
    // unwrapping Optionals, formatting results.
    // Pattern: collectingAndThen(downstreamCollector, finisherFunction)

    // TODO 1.1: Get all employee names as an UNMODIFIABLE list
    // Question: What if you need to return a collection that shouldn't be modified by the caller?
    List<String> employeeNames = employees.stream().collect(Collectors.collectingAndThen(
        Collectors.mapping(Employee::getName, Collectors.toList()), Collections::unmodifiableList));

    // Alternative(and better):
    // List<String> employeeNames =
    // employees.stream().map(Employee::getName).collect(Collectors.toUnmodifiableList());

    // TODO 1.2: Group employees by department, then get the SIZE of the map (how many departments)
    // Question: What if you don't need the grouped data itself, just metadata about it?
    int departmentCount = employees.stream().collect(
        Collectors.collectingAndThen(Collectors.groupingBy(Employee::getDepartment), Map::size));

    // Alternative(and better):
    // long departmentCount = employees.stream().map(Employee::getDepartment).distinct().count();

    // TODO 1.3: Find the employee with max salary, then get their name (or "NONE" if empty)
    // Question: How do you safely unwrap an Optional and provide a default in one operation?
    String highestPaidName = employees.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
            (opt) -> opt.map(Employee::getName).orElse("NONE")));

    // Alternative(and better):
    // String highestPaidName = employees.stream().max(Comparator.comparingInt(Employee::getSalary))
    // .map(Employee::getName).orElse("NONE");

    // TODO 1.4: Get all departments as a sorted unmodifiable list
    // Question: How do you chain transformations - first collect to remove duplicates, then sort,
    // then make immutable?
    List<String> sortedDepartments = employees.stream().map(Employee::getDepartment)
        .collect(Collectors.collectingAndThen(Collectors.toSet(), (set) -> {
          List<String> sorted = new ArrayList<>(set);
          sorted.sort(Comparator.naturalOrder());
          return Collections.unmodifiableList(sorted);
        }));

    // Alternative(and better):
    // List<String> sortedDepartments = employees.stream().map(Employee::getDepartment).distinct()
    // .sorted().collect(Collectors.toUnmodifiableList());

    // TODO 1.5: Group employees by department, then count how many departments have > 2 employees
    // Question: What if you need to analyze the grouped result before returning it?
    long largeDeptsCount = employees.stream()
        .collect(Collectors.collectingAndThen(Collectors.groupingBy(Employee::getDepartment),
            (map) -> map.values().stream().filter((list) -> list.size() > 2).count()));

    // Alternative(and better):
    // long largeDeptsCount =
    // employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)).values().stream()
    // .filter(list -> list.size() > 2).count();


    // TODO 1.6: Calculate average salary, then format as currency string "$XX,XXX"
    // Question: How do you transform a numeric result into a formatted string in one pipeline?
    String avgSalaryFormatted = employees.stream().collect(Collectors.collectingAndThen(
        Collectors.averagingInt(Employee::getSalary), (avg) -> String.format("$%,.0f", avg)));

    // Alternative:
    // double avg = employees.stream().mapToInt(Employee::getSalary).average().orElse(0.0);
    // String avgSalaryFormatted = String.format("$%,.0f", avg);

    // TODO 1.7: Get Engineering employees as a comma-separated string of names
    // Question: How do you add a prefix/wrapper to a joined string result?
    String engineersList = employees.stream()
        .filter(emp -> "Engineering".equals(emp.getDepartment())).map(Employee::getName).collect(
            Collectors.collectingAndThen(Collectors.joining(", "), list -> "Engineers: " + list));

    // Alternative(simpler):
    // String engineersList =
    // "Engineers: " + employees.stream().filter(emp -> "Engineering".equals(emp.getDepartment()))
    // .map(Employee::getName).collect(Collectors.joining(", "));

    // TODO 1.8: Find min salary employee, get their full info string or "No employees"
    // Question: How do you handle Optional results with custom default messages?
    String lowestPaidInfo = employees.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.minBy(Comparator.comparingInt(Employee::getSalary)),
            opt -> opt.map(Employee::toString).orElse("No employees")));

    // Alternative(and better):
    // String lowestPaidInfo = employees.stream().min(Comparator.comparingInt(Employee::getSalary))
    // .map(Employee::getName).orElse("No employees");


    // ========== SECTION 2: TEEING ==========
    // WHY: teeing() performs TWO independent operations on the SAME stream in ONE pass.
    // Without teeing, you'd need two stream pipelines (inefficient) or collect to intermediate
    // collection.
    // Use cases: calculating multiple statistics, finding min AND max, getting data AND metadata
    // simultaneously.
    // Pattern: teeing(collector1, collector2, (result1, result2) -> combineFunction)
    // Key insight: Both collectors see ALL elements, then results are merged.

    // TODO 2.1: Calculate BOTH sum and count of all salaries in one pass, return as "Sum: X, Count:
    // Y"
    // Question: How do you calculate two different aggregations without iterating twice?
    String salaryStats =
        employees.stream().collect(Collectors.teeing(Collectors.summingInt(Employee::getSalary),
            Collectors.counting(), (sum, count) -> "Sum: " + sum + ", Count: " + count));

    // TODO 2.2: Find BOTH min and max salary, return as a Pair or formatted string "Min: X, Max: Y"
    // Question: How do you find both extremes in a single traversal?
    String salaryRange = employees.stream()
        .collect(Collectors.teeing(Collectors.minBy(Comparator.comparingInt(Employee::getSalary)),
            Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
            (minOpt, maxOpt) -> "Min: " + minOpt.map(Employee::getSalary).orElse(0) + ", Max: "
                + maxOpt.map(Employee::getSalary).orElse(0)));

    // Alternative(and better):
    // IntSummaryStatistics stats =
    // employees.stream().mapToInt(Employee::getSalary).summaryStatistics();
    // String salaryRange = "Min: " + stats.getMin() + ", Max: " + stats.getMax();

    // TODO 2.3: For Sales department only: get both average salary AND list of all names
    // Return as Map with keys "average" and "names"
    // Question: How do you extract different types of data (numeric + list) from the same filtered
    // stream?
    Map<String, Object> salesDeptStats =
        employees.stream().filter(emp -> "Sales".equals(emp.getDepartment()))
            .collect(Collectors.teeing(Collectors.averagingInt(Employee::getSalary),
                Collectors.mapping(Employee::getName, Collectors.toList()), (avgSalary, names) -> {
                  Map<String, Object> result = new HashMap<>();
                  result.put("average", avgSalary);
                  result.put("names", names);
                  return result;
                }));

    // TODO 2.4: Calculate total sales amount AND total quantity sold (across all sales)
    // Return as formatted string "Total: $X, Units: Y"
    // Question: How do you aggregate two different fields simultaneously?
    String salesSummary = sales.stream()
        .collect(Collectors.teeing(Collectors.summingDouble(Sale::getAmount),
            Collectors.summingInt(Sale::getQuantity),
            (amount, quantity) -> "Total: $" + amount + ", Units: " + quantity));

    // Alternative(and better):
    // double totalAmount = sales.stream().mapToDouble(Sale::getAmount).sum();
    // int totalQuantity = sales.stream().mapToInt(Sale::getQuantity).sum();
    // String salesSummary = String.format("Total: $%.0f, Units: %d", totalAmount, totalQuantity);

    // TODO 2.5: Get both the product with highest total revenue AND the count of distinct products
    // Question: How do you find a maximum while also counting elements?
    // Challenge: Revenue requires grouping first - think about stream structure.

    // It's pointless to do this exercise using teeing
    Map<String, Double> productRevenue =
        sales.stream().collect(Collectors.groupingBy(Sale::getProduct,
            Collectors.summingDouble(s -> s.getAmount() * s.getQuantity())));
    String topProductAndCount =
        "Top product: " + productRevenue.entrySet().stream().max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse("none") + " / Count: " + productRevenue.size();

    // TODO 2.6: For employees with >5 years experience: get both their average salary AND their
    // count
    // Return formatted as "Senior employees: X, Avg salary: $Y"
    // Question: How do you calculate average while simultaneously counting the elements?
    String seniorStats = employees.stream().filter(emp -> emp.getYearsExperience() > 5)
        .collect(Collectors.teeing(Collectors.averagingInt(Employee::getSalary),
            Collectors.counting(), (avgSalary, count) -> String
                .format("Senior employees: %d, Avg salary: $%.0f", count, avgSalary)));

    // Alternative(and better):
    // IntSummaryStatistics stats = employees.stream().filter(emp -> emp.getYearsExperience() > 5)
    // .mapToInt(Employee::getSalary).summaryStatistics();
    // String seniorStats = String.format("Senior employees: %d, Avg salary: $%.0f",
    // stats.getCount(),
    // stats.getAverage());

    // ========== SECTION 3: COMPLEX MULTI-STAGE PIPELINES ==========
    // WHY: Real-world data problems require chaining multiple transformations.
    // Skills needed: grouping, filtering, mapping, sorting, finding extremes, nested operations.
    // Key pattern: intermediate collections -> further processing -> final result.
    // Think: What data structure do I need first? What operations come next?

    // TODO 3.1: Group employees by department, then for each dept get the highest salary
    // Result: Map<String, Integer>
    // Question: How do you group and then aggregate within each group?
    Map<String, Integer> maxSalaryByDept = null;

    // TODO 3.2: Group sales by region, then for each region get the list of DISTINCT products sold
    // Result: Map<String, List<String>>
    // Question: How do you extract and deduplicate nested values within groups?
    Map<String, List<String>> productsByRegion = null;

    // TODO 3.3: Find the department with the highest average salary
    // Steps: group by dept -> calculate avg for each -> find max entry
    // Return department name or "NONE"
    // Question: How do you find the maximum based on a calculated value (not a direct field)?
    String highestAvgSalaryDept = null;

    // TODO 3.4: For each product, calculate total revenue (amount * quantity), return top 3
    // products by revenue
    // Result: List of product names
    // Question: How do you group, calculate per-group totals, sort, and limit?
    List<String> top3Products = null;

    // TODO 3.5: Group employees by department, but only include employees with salary > 70k
    // Then for each dept, get list of names
    // Result: Map<String, List<String>>
    // Question: Should you filter before or after grouping? What's more efficient?
    Map<String, List<String>> highEarnersByDept = null;

    // TODO 3.6: Create a report: for each department, show "Dept: X (Y employees, avg: $Z)"
    // Result: List<String>
    // Question: How do you group and then perform multiple calculations per group for formatting?
    List<String> deptReports = null;

    // TODO 3.7: Find all employees who earn more than the average salary of their department
    // This requires: 1) Calculate avg per dept, 2) Filter employees based on their dept's avg
    // Result: List<Employee>
    // Question: How do you use one stream's results (averages) to filter another stream?
    // Challenge: This likely requires an intermediate Map of averages.
    List<Employee> aboveAvgInDept = null;

    // TODO 3.8: Nested grouping: Group sales by region, then by product, then sum the amounts
    // Result: Map<String, Map<String, Double>>
    // Question: How do you create two levels of grouping with aggregation at the innermost level?
    Map<String, Map<String, Double>> regionProductRevenue = null;

    // ========== SECTION 4: EDGE CASES AND TRICKY SCENARIOS ==========
    // WHY: Production code must handle empty collections, nulls, and missing data gracefully.
    // These scenarios expose whether you truly understand Optional, empty stream behavior,
    // and defensive programming. Streams don't crash on empty - but do YOU handle the results
    // correctly?

    // Empty list test data
    List<Employee> emptyEmployees = new ArrayList<>();

    // TODO 4.1: Calculate average salary from EMPTY list, return 0.0 if empty
    // Question: What does averagingInt return for an empty stream? How do you handle it?
    double avgOfEmpty = 0.0;

    // TODO 4.2: Group empty list by department - should return empty map
    // Question: Does groupingBy crash on empty streams? What's the return type?
    Map<String, List<Employee>> emptyGrouping = null;

    // TODO 4.3: Handle null values - some employees have null departments (add test data)
    List<Employee> employeesWithNulls = Arrays.asList(new Employee("John", null, 50000, 1),
        new Employee("Jane", "IT", 60000, 2), new Employee("Jack", null, 55000, 3));
    // Group by department, handling nulls appropriately
    // Question: Does groupingBy accept null keys? Should you filter nulls or keep them?
    Map<String, List<Employee>> groupedHandlingNulls = null;

    // TODO 4.4: Find max salary, but if list is empty return Optional.empty() not crash
    // Question: What's the proper return type for operations that might have no result?
    Optional<Integer> maxSalaryOptional = null;

    // ========== SECTION 5: CUSTOM COLLECTORS ==========
    // WHY: Sometimes built-in collectors aren't enough. Maybe you need a specific data structure
    // (TreeSet), or custom accumulation logic, or specialized formatting. Custom collectors give
    // you
    // full control over the collection process: supplier (create container), accumulator (add
    // elements),
    // combiner (merge parallel results), finisher (optional final transformation).
    // Pattern: Collector.of(supplier, accumulator, combiner, [finisher], [characteristics])

    // TODO 5.1: Create a custom collector that collects to a TreeSet (sorted, no duplicates)
    // Question: What are the three essential functions needed to collect into any container?
    // Think: How do you create a TreeSet? How do you add to it? How do you merge two TreeSets?
    Set<String> sortedUniqueDepts = null;

    // TODO 5.2: Custom collector that concatenates employee names with " | " separator
    // But adds department prefix: "Engineering: Alice | Engineering: Bob | Sales: Charlie"
    // Question: How do you build a custom accumulator that formats as it collects?
    // Challenge: Can you do this with Collector.of() or do you need StringBuilder accumulation?
    String customConcatenated = null;

    // ========== TEST YOUR SOLUTIONS ==========

    System.out.println("=== SECTION 1: COLLECTING_AND_THEN ===");
    System.out.println("1.1 Employee names: " + employeeNames); // Try to modify (should throw
    // exception): // employeeNames.add("Test"); // Should throw UnsupportedOperationException

    System.out.println("1.2 Department count: " + departmentCount);
    System.out.println("1.3 Highest paid: " + highestPaidName);
    System.out.println("1.4 Sorted departments: " + sortedDepartments);
    System.out.println("1.5 Large departments count: " + largeDeptsCount);
    System.out.println("1.6 Avg salary formatted: " + avgSalaryFormatted);
    System.out.println("1.7 Engineers list: " + engineersList);
    System.out.println("1.8 Lowest paid info: " + lowestPaidInfo);

    System.out.println("\n=== SECTION 2: TEEING ===");
    System.out.println("2.1 Salary stats: " + salaryStats);
    System.out.println("2.2 Salary range: " + salaryRange);
    System.out.println("2.3 Sales dept stats: " + salesDeptStats);
    System.out.println("2.4 Sales summary: " + salesSummary);
    System.out.println("2.5 Top product and count: " + topProductAndCount);
    System.out.println("2.6 Senior stats: " + seniorStats);

    System.out.println("\n=== SECTION 3: COMPLEX PIPELINES ===");
    System.out.println("3.1 Max salary by dept: " + maxSalaryByDept);
    System.out.println("3.2 Products by region: " + productsByRegion);
    System.out.println("3.3 Highest avg salary dept: " + highestAvgSalaryDept);
    System.out.println("3.4 Top 3 products: " + top3Products);
    System.out.println("3.5 High earners by dept: " + highEarnersByDept);
    System.out.println("3.6 Dept reports: " + deptReports);
    System.out.println("3.7 Above avg in dept: " + aboveAvgInDept);
    System.out.println("3.8 Region-Product revenue: " + regionProductRevenue);

    System.out.println("\n=== SECTION 4: EDGE CASES ===");
    System.out.println("4.1 Avg of empty: " + avgOfEmpty);
    System.out.println("4.2 Empty grouping: " + emptyGrouping);
    System.out.println("4.3 Grouped handling nulls: " + groupedHandlingNulls);
    System.out.println("4.4 Max salary optional: " + maxSalaryOptional);

    System.out.println("\n=== SECTION 5: CUSTOM COLLECTORS ===");
    System.out.println("5.1 Sorted unique depts: " + sortedUniqueDepts);
    System.out.println("5.2 Custom concatenated: " + customConcatenated);
  }
}
