package methods_Przyklad;

public class przyklad {
  static void myMethod(String fname,int wiek) {
    System.out.println(fname + " nazwisko lat :"+ wiek);}
  static int dodajInt(int x, int y) {
    return x + y;
  }

  static double dodajDouble(double x, double y) {
    return x + y;
  }
    
  

  public static void main(String[] args) {
    myMethod("mikolaj",16);
    myMethod("krystian",16);
    myMethod("gabriela",16);
    myMethod("krzysztof",16);
  
    
   int nr = dodajInt(24,832);
   double nr2 = dodajDouble(25.32,41.41);
   

   System.out.println(nr + nr2);
  
  
  }
}