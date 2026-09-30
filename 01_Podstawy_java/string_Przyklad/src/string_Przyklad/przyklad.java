package string_Przyklad;

public class przyklad {
  public static void main(String[] args) {
    String nr = "67";
    String nr2 ="218";
    String text ="dodawanie string";
    
    System.out.println(nr + nr2 + text );//okazanie dodawania stringow 67+218 = 67218 
    System.out.println(text.length());// wypisanie dlugosci stringu
    System.out.println(text.toUpperCase());//zamiana na wielkie litery
    System.out.println(text.indexOf("str"));//podaje index poczatku wyszukanego slowa w string
    
    String txt1 = "Hello";
    String txt2 = "Hello";

    String txt3 = "Greetings";
    String txt4 = "Great things";

    System.out.println(txt1.equals(txt2));  // true
    System.out.println(txt3.equals(txt4));  // false
    
    String txt = "   Hello World   ";
    System.out.println("przed: [" + txt + "]");
    System.out.println("po:  [" + txt.trim() + "]");
    
    String firstName = "Krzysztof";
    String lastName = "Długosz";
    System.out.println(firstName + " " + lastName);
  
    String txt7 = "uzycie cudzyslowiu \"cudzyslow\" .";
    System.out.println(txt7);

  }
}
