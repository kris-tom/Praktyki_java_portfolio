package zad5;

public class Main {
  public static void aMethod() throws Exception//program wypisze: finally exception finished
  {
    try /* Line 5 */
    {
      throw new Exception(); /* Line 7 */
    } finally /* Line 9 */
    {
      System.out.print("finally "); /* Line 11 */
    }
  }

  public static void main(String args[]) {
    try {
      aMethod();
    } catch (Exception e) /* Line 20 */
    {
      System.out.print("exception ");
    }
    System.out.print("finished"); /* Line 24 */
  }
}
