package zad1;

public class Main {
    public static void main(String[] args) {

        Basket<Fruit> basket = new Basket<>();

        Apple apple1 = new Apple();
        apple1.state = "Świeże";

        Peach peach = new Peach();
        Strawberry strawberry = new Strawberry();

        basket.addFruit(apple1);
        basket.addFruit(peach);
        basket.addFruit(strawberry);

        System.out.println(basket.fruitAtIndex(0)); // Apple
        System.out.println(basket.fruitAtIndex(1)); // Peach
        System.out.println(basket.fruitAtIndex(2)); // Strawberry

        System.out.println("i'ty element: "+basket.fruitAtIndex(2));
        System.out.println("Total fruits: " + basket.countAll());
    }
}