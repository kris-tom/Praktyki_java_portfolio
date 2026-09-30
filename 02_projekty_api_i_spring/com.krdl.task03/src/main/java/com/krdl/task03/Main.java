package com.krdl.task03;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Queue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        while (i < 10) {
            System.out.print("Dodaj: ");
            int input = scanner.nextInt();
            pq.offer(input);
            i++;
        }

        System.out.print("Posortowana kolejka (rosnąco): ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();

        scanner.close();
    }
}