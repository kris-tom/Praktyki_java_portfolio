package com.krdl.task01;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String input;
    ArrayList<String> lista = new ArrayList<>();

    System.out.println("Wpisuj tekst (wpisz '-' aby zakończyć):");

    while (true) {
      input = sc.nextLine();

      if ("-".equalsIgnoreCase(input)) {
        break;
      }

      lista.add(input);
    }
    Collections.sort(lista);

    System.out.println("Wpisane imiona:");
    for (String linia : lista) {
      System.out.println(linia);
    }

    sc.close();
  }
}