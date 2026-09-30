package com.krdl.task02;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Map<String, String> pary = new HashMap<>();
    List<String> damskie = new ArrayList<>();

    System.out.println("Wpisuj pary imion (ImięDamskie - ImięMęskie), wpisz '-' aby zakończyć:");

    while (true) {
      String input = sc.nextLine();
      if ("-".equalsIgnoreCase(input))
        break;

      int index = input.indexOf(" - ");
      if (index != -1) {
        String d = input.substring(0, index);
        String m = input.substring(index + 3);
        pary.put(d, m);
        damskie.add(d);
      }
    }

    Collections.sort(damskie);
    System.out.println("\nDamskie imiona (alfabetycznie):");
    for (String imie : damskie) {
      System.out.println(imie);
    }

    System.out.print("\nPodaj imię, aby znaleźć partnera: ");
    String imie = sc.nextLine();

    String partner = null;

    if (pary.containsKey(imie)) {
      partner = pary.get(imie);
    } else {

      for (Map.Entry<String, String> entry : pary.entrySet()) {
        if (entry.getValue().equals(imie)) {
          partner = entry.getKey();
          break;
        }
      }
    }

    if (partner != null) {
      System.out.println("Partner: " + partner);
    } else {
      System.out.println("Nie znaleziono partnera.");
    }

    sc.close();
  }
}