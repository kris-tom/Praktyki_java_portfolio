package com.krdl.task10;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final List<String> ANIMAL_NAMES = List.of(
        "Burek", "Mruczek", "Reksio", "Azor", "Luna",
        "Puszek", "Filemon", "Kicia", "Basia", "Zefir"
    );
    static final List<String> ANIMAL_TYPES = List.of(
        "pies", "kot", "królik", "chomik", "papuga"
    );
    static final List<String> OWNER_NAMES = List.of(
        "Jan Kowalski", "Anna Nowak", "Piotr Wiśniewski",
        "Maria Wójcik", "Tomasz Kowalczyk", "Katarzyna Kamińska",
        "Marek Lewandowski", "Agnieszka Zielińska", "Paweł Szymański", "Ewa Woźniak"
    );
    static final List<String> ADDRESSES = List.of(
        "ul. Różana 1, Warszawa", "ul. Lipowa 5, Kraków",
        "ul. Słoneczna 3, Gdańsk", "ul. Leśna 7, Wrocław",
        "ul. Polna 2, Poznań", "ul. Kwiatowa 9, Łódź",
        "ul. Brzozowa 4, Katowice", "ul. Sosnowa 6, Lublin",
        "ul. Dębowa 8, Białystok", "ul. Jodłowa 10, Szczecin"
    );

    public static void main(String[] args) {
        Random random = new Random();
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.now();
        long startEpoch = start.toEpochDay();
        long endEpoch = end.toEpochDay();

        // Generowanie 10 właścicieli
        List<Owner> owners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int animalCount = random.nextInt(21); // 0-20
            Set<Animal> animals = new HashSet<>();
            for (int j = 0; j < animalCount; j++) {
                String aName = ANIMAL_NAMES.get(random.nextInt(ANIMAL_NAMES.size()));
                String aType = ANIMAL_TYPES.get(random.nextInt(ANIMAL_TYPES.size()));
                long randomDay = startEpoch + (long)(Math.random() * (endEpoch - startEpoch));
                LocalDate dob = LocalDate.ofEpochDay(randomDay);
                animals.add(new Animal(aName + j, aType, dob));
            }
            owners.add(new Owner(OWNER_NAMES.get(i), ADDRESSES.get(i), animals));
        }

        // Wypisanie właścicieli i ich zwierząt
        System.out.println("=== WŁAŚCICIELE I ICH ZWIERZĘTA ===");
        owners.forEach(System.out::println);

        // 1. Liczba zwierząt - mapToInt + sum
        int totalMapToInt = owners.stream()
            .mapToInt(o -> o.getAnimals().size())
            .sum();
        System.out.println("1. Liczba zwierząt (mapToInt + sum): " + totalMapToInt);

        // 2. Liczba zwierząt - reduce
        int totalReduce = owners.stream()
            .map(o -> o.getAnimals().size())
            .reduce(0, Integer::sum);
        System.out.println("2. Liczba zwierząt (reduce): " + totalReduce);

        // 3. Lista wszystkich zwierząt - flatMap
        List<Animal> allAnimals = owners.stream()
            .flatMap(o -> o.getAnimals().stream())
            .collect(Collectors.toList());
        System.out.println("3. Wszystkie zwierzęta (flatMap): " + allAnimals);

        // 4. Zwierzęta urodzone po 2022-01-01 - filter
        LocalDate cutoff = LocalDate.of(2022, 1, 1);
        List<Animal> after2022 = allAnimals.stream()
            .filter(a -> a.getDateOfBirth().isAfter(cutoff))
            .collect(Collectors.toList());
        System.out.println("4. Zwierzęta urodzone po 2022-01-01: " + after2022);

        // 5. Jakiekolwiek zwierzę urodzone po 2022-01-01 - findAny
        Optional<Animal> anyAfter2022 = allAnimals.stream()
            .filter(a -> a.getDateOfBirth().isAfter(cutoff))
            .findAny();
        System.out.println("5. Jakiekolwiek zwierzę po 2022-01-01: "
            + anyAfter2022.orElse(null));

        // 6. Najstarsze zwierzę urodzone po 2022-01-01 (najwcześniejsza data)
        Optional<Animal> oldestAfter2022 = allAnimals.stream()
            .filter(a -> a.getDateOfBirth().isAfter(cutoff))
            .min(Comparator.comparing(Animal::getDateOfBirth));
        System.out.println("6. Najstarsze zwierzę po 2022-01-01: "
            + oldestAfter2022.orElse(null));
    }
}