package zad1;

import java.util.ArrayList;
import java.util.List;

public class Basket<T> {

    private List<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

   
    public T fruitAtIndex(int index) {
        if (index < 0 || index >= fruits.size()) {
            throw new IndexOutOfBoundsException("Nieprawidłowy indeks: " + index);
        }
        return fruits.get(index);
    }

 
    public int countAll() {
        return fruits.size();
    }
}