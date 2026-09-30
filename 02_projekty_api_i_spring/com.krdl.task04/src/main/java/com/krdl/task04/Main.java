package com.krdl.task04;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        while (i < 10) {
            System.out.print("Dodaj: ");
            int input = scanner.nextInt();
            stack.push(input); // dodaj na "wierzch" stosu
            i++;
        }

        System.out.print("Elementy w odwrotnej kolejności wprowadzania: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " "); // pobierz z "wierzchu" — ostatni dodany
        }

        System.out.println();
        scanner.close();
    }
}