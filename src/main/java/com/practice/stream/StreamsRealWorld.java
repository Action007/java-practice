package com.practice.stream;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsRealWorld {

  static class Transaction {
    private int id;
    private String type; // "DEPOSIT" or "WITHDRAWAL"
    private double amount;
    private String accountId;

    public Transaction(int id, String type, double amount, String accountId) {
      this.id = id;
      this.type = type;
      this.amount = amount;
      this.accountId = accountId;
    }

    public int getId() {
      return id;
    }

    public String getType() {
      return type;
    }

    public double getAmount() {
      return amount;
    }

    public String getAccountId() {
      return accountId;
    }

    @Override
    public String toString() {
      return String.format("Tx#%d: %s $%.2f [%s]", id, type, amount, accountId);
    }
  }

  static class Student {
    private String name;
    private List<Integer> grades;
    private String major;

    public Student(String name, List<Integer> grades, String major) {
      this.name = name;
      this.grades = grades;
      this.major = major;
    }

    public String getName() {
      return name;
    }

    public List<Integer> getGrades() {
      return grades;
    }

    public String getMajor() {
      return major;
    }

    @Override
    public String toString() {
      return String.format("%s (%s): %s", name, major, grades);
    }
  }

  public static void main(String[] args) {
    List<Transaction> transactions = Arrays.asList(new Transaction(1, "DEPOSIT", 500.0, "ACC001"),
        new Transaction(2, "WITHDRAWAL", 200.0, "ACC001"),
        new Transaction(3, "DEPOSIT", 1000.0, "ACC002"),
        new Transaction(4, "DEPOSIT", 300.0, "ACC001"),
        new Transaction(5, "WITHDRAWAL", 150.0, "ACC002"),
        new Transaction(6, "DEPOSIT", 700.0, "ACC003"),
        new Transaction(7, "WITHDRAWAL", 400.0, "ACC002"),
        new Transaction(8, "DEPOSIT", 250.0, "ACC003"));

    List<Student> students =
        Arrays.asList(new Student("Alice", Arrays.asList(85, 90, 78, 92), "Computer Science"),
            new Student("Bob", Arrays.asList(70, 75, 68, 72), "Mathematics"),
            new Student("Charlie", Arrays.asList(95, 88, 91, 89), "Computer Science"),
            new Student("David", Arrays.asList(60, 65, 58, 62), "Physics"),
            new Student("Eve", Arrays.asList(88, 84, 90, 86), "Mathematics"));

    // ========== SCENARIO 1: Banking - Calculate Account Balances ==========

    // TODO: Calculate net balance for each account
    // DEPOSITS add to balance, WITHDRAWALS subtract
    // Steps:
    // 1. Group transactions by accountId
    // 2. For each account, sum: (DEPOSIT amount) - (WITHDRAWAL amount)
    // Result: Map<String, Double> like {"ACC001": 600.0, "ACC002": 450.0, ...}

    // HINT: You'll need groupingBy with a custom collector
    // One approach: groupingBy(accountId, summingDouble with conditional logic)
    // Better approach: groupingBy then manual calculation with reduce

    // Map<String, Double> balanceMap = transactions.stream()
    // .collect(Collectors.groupingBy((elem) -> elem.getAccountId(), Collectors.summingDouble(
    // (elem) -> elem.getType().equals("DEPOSIT") ? elem.getAmount() : -elem.getAmount())));

    Map<String, Double> accountBalances =
        transactions.stream()
            .collect(Collectors.groupingBy((elem) -> elem.getAccountId(), Collectors.reducing(0.0,
                (elem) -> elem.getType().equals("DEPOSIT") ? elem.getAmount() : -elem.getAmount(),
                (elem, elem2) -> elem + elem2)));

    // System.out.println(accountBalances);

    // TODO: Find accounts with balance > 500
    // Hint: Filter the map entries
    List<String> highBalanceAccounts =
        accountBalances.entrySet().stream().filter((balance) -> balance.getValue() > 500)
            .map(Map.Entry::getKey).collect(Collectors.toList());
    // System.err.println(highBalanceAccounts);

    // ========== SCENARIO 2: Grade Analysis ==========

    // TODO: Calculate average grade for each student
    // Result: Map<String, Double>
    Map<String, Double> averageGrades =
        students.stream().collect(Collectors.toMap(Student::getName, (student) -> student
            .getGrades().stream().mapToInt(Integer::intValue).average().orElse(0.0)));
    // System.out.println(averageGrades);

    // TODO: Get students with average grade >= 80
    // You need to filter students based on their calculated average
    List<Student> topStudents = students.stream()
        .filter(((student) -> averageGrades.get(student.getName()) >= 80)).toList();
    // System.out.println(topStudents);

    // TODO: Get all individual grades across all students (flatten)
    List<Integer> allGrades = students.stream().flatMap((student) -> student.getGrades().stream())
        .collect(Collectors.toList());
    // System.out.println(allGrades);

    // TODO: Calculate overall class average (all grades from all students)
    double classAverage = allGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    // System.out.println(classAverage);

    // TODO: Get highest grade achieved by each major
    // Group by major, then find max grade across all students in that major
    // Hint: Use flatMapToInt to get all grades per major group
    Map<String, Optional<Integer>> highestGradeByMajor = null;

    // ========== SCENARIO 3: Transaction Analysis ==========

    // TODO: Get total DEPOSIT amount across all accounts
    double totalDeposits = 0.0;

    // TODO: Get total WITHDRAWAL amount across all accounts
    double totalWithdrawals = 0.0;

    // TODO: Find the largest single transaction (by amount)
    Optional<Transaction> largestTransaction = null;

    // TODO: Count transactions per account
    Map<String, Long> transactionCounts = null;

    // TODO: Get account IDs that have more than 2 transactions
    List<String> activeAccounts = null;

    // ========== SCENARIO 4: Data Transformation ==========

    // TODO: Create a report: "Alice (CS): avg 86.25"
    // Format: "Name (Major): avg XX.XX" for all students
    List<String> studentReports = null;

    // TODO: Create lookup map: Transaction ID -> Account ID
    Map<Integer, String> txToAccount = null;

    // TODO: Get comma-separated string of all account IDs (unique)
    String accountsList = null;

    // ========== SCENARIO 5: Complex Filtering & Grouping ==========

    // TODO: Group students by major, but only include students with average >= 75
    // Result: Map<String, List<Student>>
    Map<String, List<Student>> passingStudentsByMajor = null;

    // TODO: Get transactions for ACC001, sorted by amount descending
    List<Transaction> acc001Transactions = null;

    // TODO: Create a map of account -> list of transaction amounts (not full objects)
    // Result: Map<String, List<Double>>
    Map<String, List<Double>> amountsByAccount = null;

    // ========== SCENARIO 6: Primitive Streams (Performance) ==========

    // TODO: Generate list of numbers 1-100 using IntStream
    List<Integer> oneToHundred = null;

    // TODO: Sum of all even numbers from 1-100
    int sumOfEvens = 0;

    // TODO: Find first 10 numbers divisible by 7 (from 1-1000)
    List<Integer> divisibleBy7 = null;

    // ========== SCENARIO 7: Error Handling with Optional ==========

    // TODO: Find student named "Alice" - return Optional<Student>
    Optional<Student> alice = null;

    // TODO: Get Alice's average grade, or 0.0 if not found
    // Hint: Use .map() and .orElse()
    double aliceAverage = 0.0;

    // TODO: Get first grade of student "Zack" (doesn't exist), or -1 if not found
    int zackFirstGrade = -1;

    // ========== SCENARIO 8: Pagination Pattern ==========

    // TODO: Implement pagination for transactions
    // Page size: 3, Get page 2 (transactions 4-6)
    // Hint: skip((page-1) * pageSize).limit(pageSize)
    int pageSize = 3;
    int pageNumber = 2;
    List<Transaction> page2 = null;

    // ========== SCENARIO 9: Custom Aggregation ==========

    // TODO: For each account, create a summary string:
    // "ACC001: 3 transactions, balance: $600.00"
    // This combines counting and balance calculation
    Map<String, String> accountSummaries = null;

    // ========== SCENARIO 10: Real-World Pattern - Filter Chain ==========

    // TODO: Get names of CS students with average >= 85, sorted alphabetically
    // This is a common pattern: filter -> calculate -> filter -> transform -> sort
    List<String> topCSStudents = null;

    // ====== USAGE EXAMPLES (Uncomment after implementing) ======

    // System.out.println("=== SCENARIO 1: Banking ===");
    // System.out.println("Account balances: " + accountBalances);
    // System.out.println("High balance accounts: " + highBalanceAccounts);
    //
    // System.out.println("\n=== SCENARIO 2: Grade Analysis ===");
    // System.out.println("Average grades: " + averageGrades);
    // System.out.println("Top students: " + topStudents);
    // System.out.println("All grades: " + allGrades);
    // System.out.println("Class average: " + classAverage);
    // System.out.println("Highest grade by major: " + highestGradeByMajor);
    //
    // System.out.println("\n=== SCENARIO 3: Transaction Analysis ===");
    // System.out.println("Total deposits: $" + totalDeposits);
    // System.out.println("Total withdrawals: $" + totalWithdrawals);
    // System.out.println("Largest transaction: " + largestTransaction);
    // System.out.println("Transaction counts: " + transactionCounts);
    // System.out.println("Active accounts: " + activeAccounts);
    //
    // System.out.println("\n=== SCENARIO 4: Data Transformation ===");
    // System.out.println("Student reports: " + studentReports);
    // System.out.println("TX to Account map: " + txToAccount);
    // System.out.println("Accounts list: " + accountsList);
    //
    // System.out.println("\n=== SCENARIO 5: Complex Filtering & Grouping ===");
    // System.out.println("Passing students by major: " + passingStudentsByMajor);
    // System.out.println("ACC001 transactions: " + acc001Transactions);
    // System.out.println("Amounts by account: " + amountsByAccount);
    //
    // System.out.println("\n=== SCENARIO 6: Primitive Streams ===");
    // System.out.println("1-100: " + oneToHundred);
    // System.out.println("Sum of evens: " + sumOfEvens);
    // System.out.println("Divisible by 7: " + divisibleBy7);
    //
    // System.out.println("\n=== SCENARIO 7: Optional Error Handling ===");
    // System.out.println("Alice: " + alice);
    // System.out.println("Alice average: " + aliceAverage);
    // System.out.println("Zack first grade: " + zackFirstGrade);

    // System.out.println("\n=== SCENARIO 8: Pagination ===");
    // System.out.println("Page 2: " + page2);
    //
    // System.out.println("\n=== SCENARIO 9: Custom Aggregation ===");
    // System.out.println("Account summaries: " + accountSummaries);
    //
    // System.out.println("\n=== SCENARIO 10: Filter Chain ===");
    // System.out.println("Top CS students: " + topCSStudents);
    //
  }
}
