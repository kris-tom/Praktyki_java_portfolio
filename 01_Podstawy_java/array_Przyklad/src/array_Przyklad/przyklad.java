package array_Przyklad;

public class przyklad {
  public static void main(String[] args) {
    
    String[] auta = {"Volvo", "BMW", "Ford", "Mazda"};
    System.out.println(auta[2]);
    
    int[] numbers = {10, 20, 30, 40};

    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i]);
    }
    int[] numbers2 = {1, 5, 10, 25};
    int sum = 0;

    // Loop through the array and add each element to sum
    for (int i = 0; i < numbers2.length; i++) {
      sum += numbers2[i];
    }

    System.out.println("suma to : " + sum);
  }

}
