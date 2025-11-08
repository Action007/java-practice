package com.practice.solidandpatterns.behavioral.observer;

import java.util.Scanner;
import com.practice.solidandpatterns.behavioral.observer.observer.ABC;
import com.practice.solidandpatterns.behavioral.observer.observer.BBC;
import com.practice.solidandpatterns.behavioral.observer.observer.CNN;
import com.practice.solidandpatterns.behavioral.observer.observer.NewsAgency;
import com.practice.solidandpatterns.behavioral.observer.observer.Observer;
import com.practice.solidandpatterns.behavioral.observer.observer.Subject;

public class NewsAlertApp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Subject newsAgency = new NewsAgency();
    Observer bbc = new BBC();
    Observer cnn = new CNN();
    Observer abc = new ABC();
    newsAgency.registerObserver(bbc);
    newsAgency.registerObserver(cnn);
    newsAgency.registerObserver(abc);

    System.out.println("Enter headlines (type 'exit' to quit):");
    while (true) {
      System.out.print("Headline: ");
      String headline = scanner.nextLine();
      if ("exit".equalsIgnoreCase(headline)) {
        break;
      }

      newsAgency.notifyObserver(headline);
    }

    scanner.close();
  }
}
