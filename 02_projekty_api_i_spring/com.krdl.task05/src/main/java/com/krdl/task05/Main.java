package com.krdl.task05;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

  private static int readInt(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter an integer.");
      scanner.next();
    }
    return scanner.nextInt();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Data data = new Data();
    boolean isWorking = true;

    System.out.println("--- Filling the list ---");
    System.out.println("1. Generate random numbers in range [-100, 100]");
    System.out.println("2. Enter numbers manually (type '-' to finish)");
    System.out.print("Choice: ");

    int choice = readInt(scanner);
    ArrayList<Integer> numbers = new ArrayList<>();

    if (choice == 1) {
      System.out.print("How many numbers to generate?: ");
      int n = readInt(scanner);
      Random random = new Random();

      for (int i = 0; i < n; i++) {
        numbers.add(random.nextInt(201) - 100);
      }
    } else {
      System.out.println("Enter numbers (type '-' to finish):");
      while (true) {
        String input = scanner.next();
        if (input.equals("-")) {
          break;
        }
        try {
          numbers.add(Integer.parseInt(input));
        } catch (NumberFormatException e) {
          System.out.println("This is not a number. Try again.");
        }
      }
    }

    data.setList(numbers);

    while (isWorking) {
      System.out.println("\n--- MENU ---");
      System.out.println("1. Print data");
      System.out.println("2. Calculate sum");
      System.out.println("3. Calculate average");
      System.out.println("4. Find minimum and its index");
      System.out.println("5. Count positive numbers");
      System.out.println("0. Exit");
      System.out.print("Choice: ");

      int option = readInt(scanner);

      switch (option) {
      case 1:
        data.printData();
        break;
      case 2:
        System.out.println("Sum: " + data.sum());
        break;
      case 3:
        System.out.println("Average: " + data.average());
        break;
      case 4:
        int[] result = data.min();
        if (result[1] == -1) {
          System.out.println("The list is empty.");
        } else {
          System.out.println("Minimum: " + result[0] + ", Index: " + result[1]);
        }
        break;
      case 5:
        System.out.println("Positive numbers: " + data.positiveCount());
        break;
      case 0:
        isWorking = false;
        break;
      default:
        System.out.println("Invalid choice.");
      }
    }

    scanner.close();
    System.out.println("Program finished.");
  }
}