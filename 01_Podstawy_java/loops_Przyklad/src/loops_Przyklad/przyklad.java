package loops_Przyklad;

public class przyklad {
  public static void main(String[] args) {
    
    int c = 3;

    while (c > 0) {
      System.out.println(c);
      c--;
    }

    System.out.println("koniec odliczania");


    int sum = 0;
    for (int i = 1; i <= 5; i++) {
      sum = sum + i;
    }
    System.out.println("Suma to " + sum);
  }
}
