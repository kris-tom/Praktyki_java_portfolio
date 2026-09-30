package zad2;

import java.util.ArrayList;
import java.util.List;

class NumberOperations {

  private List<Double> numbers;

  public NumberOperations() {
    this.numbers = new ArrayList<>();
  }

  public void addNumber(double number) {
    numbers.add(number);
  }

  public Double getElement(int index) {
    if (index >= 0 && index < numbers.size()) {
      return numbers.get(index);
    } else {
      System.out.println("Błąd: Indeks poza zakresem.");
      return null;
    }
  }

  public int getSize() {
    return numbers.size();
  }

  public double getSum() {
    double sum = 0;
    for (Double num : numbers) {
      sum += num;
    }
    return sum;
  }

  public static void Main(String[] args) {
    NumberOperations ops = new NumberOperations();

    ops.addNumber(10.5);
    ops.addNumber(20);
    ops.addNumber(5.5);

    System.out.println("Liczba elementów: " + ops.getSize());

    System.out.println("Element na indeksie 1: " + ops.getElement(1));

    System.out.println("Suma liczb: " + ops.getSum());
  }
}
