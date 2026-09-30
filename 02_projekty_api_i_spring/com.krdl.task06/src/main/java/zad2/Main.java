package zad2;

public class Main {

  public static void main(String[] args) {//program wypisze Arithmetic Exception occurred:  / by zero java.lang.ArithmeticException: / by zero       at zad2.Main.main(Main.java:8)      finished

    try {
      int x = 0;
      int y = 5 / x;
    } catch (ArithmeticException ae) {
      System.out.println("Arithmetic Exception occurred:");
      System.out.println(ae.getMessage());
      ae.printStackTrace();
    } catch (Exception e) {
      System.out.println("General Exception:");
      e.printStackTrace();
    }

    System.out.println("finished");
  }
}