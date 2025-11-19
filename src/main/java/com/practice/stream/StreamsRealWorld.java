package com.practice.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
      return String.format("ID: %d: %s: $%.2f, AccountID: %s", id, type, amount, accountId);
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
    Map<String, Optional<Integer>> highestGradeByMajor =
        students.stream().collect(Collectors.groupingBy(Student::getMajor, Collectors.flatMapping(
            student -> student.getGrades().stream(), Collectors.maxBy(Comparator.naturalOrder()))));
    // System.out.println(highestGradeByMajor);

    // ========== SCENARIO 3: Transaction Analysis ==========

    // TODO: Get total DEPOSIT amount across all accounts
    double totalDeposits =
        transactions.stream().filter((transaction) -> transaction.getType().equals("DEPOSIT"))
            .mapToDouble(Transaction::getAmount).sum();
    // System.out.println(totalDeposits);

    // TODO: Get total WITHDRAWAL amount across all accounts
    double totalWithdrawals =
        transactions.stream().filter((transaction) -> transaction.getType().equals("WITHDRAWAL"))
            .mapToDouble(Transaction::getAmount).sum();
    // System.out.println(totalWithdrawals);

    // TODO: Find the largest single transaction (by amount)
    Optional<Transaction> largestTransaction =
        transactions.stream().max(Comparator.comparingDouble(Transaction::getAmount));
    // System.out.println(largestTransaction);

    // TODO: Count transactions per account
    Map<String, Long> transactionCounts = transactions.stream().collect(
        Collectors.groupingBy((transaction) -> transaction.getAccountId(), Collectors.counting()));
    // System.out.println(transactionCounts);

    // TODO: Get account IDs that have more than 2 transactions
    List<String> activeAccounts = transactionCounts.entrySet().stream()
        .filter((elem) -> elem.getValue() > 2).map(Map.Entry::getKey).toList();
    // System.out.println(activeAccounts);

    // ========== SCENARIO 4: Data Transformation ==========

    // TODO: Create a report: "Alice (CS): avg 86.25"
    // Format: "Name (Major): avg XX.XX" for all students

    List<String> studentReports = students.stream().map(student -> {
      String name = student.getName();
      String major = student.getMajor();
      double avg = student.getGrades().stream().mapToInt(Integer::intValue).average().orElse(0.0);
      return String.format("%s (%s): avg %.2f", name, major, avg);
    }).toList();
    // System.out.println(studentReports);

    // This will give me: "Computer Science" -> 88.375
    // Map<String, Double> studentReports =
    // students.stream().collect(Collectors.groupingBy(Student::getMajor, Collectors.flatMapping(
    // student -> student.getGrades().stream(), Collectors.averagingDouble(grade -> grade))));

    // TODO: Create lookup map: Transaction ID -> Account ID
    Map<Integer, String> txToAccount = transactions.stream()
        .collect(Collectors.toMap(Transaction::getId, Transaction::getAccountId));
    // System.out.println(txToAccount);

    // TODO: Get comma-separated string of all account IDs (unique)
    String accountsList = transactions.stream().map(Transaction::getAccountId).distinct()
        .collect(Collectors.joining(", "));
    // System.out.println(accountsList);

    // ========== SCENARIO 5: Complex Filtering & Grouping ==========

    // TODO: Group students by major, but only include students with average >= 75
    // Result: Map<String, List<Student>>
    Map<String, List<Student>> passingStudentsByMajor = students.stream()
        .collect(Collectors.groupingBy(Student::getMajor, Collectors.filtering((student) -> {
          double avg =
              student.getGrades().stream().collect(Collectors.averagingDouble(grade -> grade));
          return avg >= 75;
        }, Collectors.toList())));
    // System.out.println(passingStudentsByMajor);

    // TODO: Get transactions for ACC001, sorted by amount descending
    List<Transaction> acc001Transactions =
        transactions.stream().filter((transaction) -> transaction.getAccountId().equals("ACC001"))
            .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed()).toList();
    // System.out.println(acc001Transactions);

    // TODO: Create a map of account -> list of transaction amounts (not full objects)
    // Result: Map<String, List<Double>>
    Map<String, List<Double>> amountsByAccount =
        transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId,
            Collectors.mapping(Transaction::getAmount, Collectors.toList())));
    // System.out.println(amountsByAccount);

    // ========== SCENARIO 6: Primitive Streams (Performance) ==========

    // TODO: Generate list of numbers 1-100 using IntStream
    List<Integer> oneToHundred = IntStream.rangeClosed(1, 100).boxed().toList();
    // System.out.println(oneToHundred);

    // TODO: Sum of all even numbers from 1-100
    int sumOfEvens = IntStream.rangeClosed(1, 100).filter((num) -> num % 2 == 0).sum();
    // System.out.println(sumOfEvens);

    // TODO: Find first 10 numbers divisible by 7 (from 1-1000)
    List<Integer> divisibleBy7 =
        IntStream.rangeClosed(1, 1000).filter((num) -> num % 7 == 0).limit(10).boxed().toList();
    // System.out.println(divisibleBy7);

    // ========== SCENARIO 7: Error Handling with Optional ==========

    // TODO: Find student named "Alice" - return Optional<Student>
    Optional<Student> alice =
        students.stream().filter((student) -> student.getName().equals("Alice")).findFirst();
    // System.out.println(alice);

    // TODO: Get Alice's average grade, or 0.0 if not found
    // Hint: Use .map() and .orElse()
    double aliceAverage = alice.map(Student::getGrades)
        .map((grades) -> grades.stream().mapToInt(Integer::intValue).average().orElse(0.0))
        .orElse(0.0);
    // System.out.println(aliceAverage);

    // TODO: Get first grade of student "Zack" (doesn't exist), or -1 if not found
    int zackFirstGrade = students.stream().filter((student) -> student.getName().equals("Zack"))
        .findFirst().map(Student::getGrades).filter((grades) -> !grades.isEmpty())
        .map((grades) -> grades.get(0)).orElse(-1);
    // System.out.println(zackFirstGrade);

    // ========== SCENARIO 8: Pagination Pattern ==========

    // TODO: Implement pagination for transactions
    // Page size: 3, Get page 2 (transactions 4-6)
    // Hint: skip((page-1) * pageSize).limit(pageSize)
    int pageSize = 3;
    int pageNumber = 2;
    List<Transaction> page2 =
        transactions.stream().skip((pageNumber - 1) * pageSize).limit(pageSize).toList();
    System.out.println(page2);

    // ========== SCENARIO 9: Custom Aggregation ==========

    // TODO: For each account, create a summary string:
    // "ACC001: 3 transactions, balance: $600.00"
    // This combines counting and balance calculation
    Map<String, String> accountSummaries =
        transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId)).entrySet()
            .stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
              List<Transaction> txs = entry.getValue();
              int count = txs.size();
              double balance = txs.stream()
                  .mapToDouble(t -> t.getType().equals("DEPOSIT") ? t.getAmount() : -t.getAmount())
                  .sum();
              return String.format("%d transactions, balance: $%.2f", count, balance);
            }));
    // System.out.println(accountSummaries);

    // ========== SCENARIO 10: Real-World Pattern - Filter Chain ==========

    // TODO: Get names of CS students with average >= 85, sorted alphabetically
    // This is a common pattern: filter -> calculate -> filter -> transform -> sort
    List<String> topCSStudents = students.stream()
        .filter((student) -> student.getMajor().equals("Computer Science")).filter((student) -> {
          double avg =
              student.getGrades().stream().mapToInt(Integer::intValue).average().orElse(0.0);
          return avg >= 85;
        }).map(Student::getName).sorted().toList();
    // System.out.println(topCSStudents);
  }
}
