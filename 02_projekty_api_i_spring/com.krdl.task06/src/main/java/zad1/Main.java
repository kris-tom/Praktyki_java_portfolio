package zad1;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Try block");
            String s = null;
            s.length(); // spowoduje NullPointerException
            return;
        } catch (NullPointerException ex) {
         
            System.out.println("Caught exception:");
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
