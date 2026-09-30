package zad3;

public class Main {
  public static void main(String[] args) {
    try {
      badMethod();
      System.out.print("A");
    } catch (RuntimeException ex) /* Line 10 *///program wypisze: BDE
    {
      System.out.print("B");
    } catch (Exception ex1) {
      System.out.print("C");
    } finally {
      System.out.print("D");
    }
    System.out.print("E");
  }

  public static void badMethod() {
    throw new RuntimeException();
  }
}
