package com.krdl.task10;

import java.util.Set;

public class Owner {
    private String name;
    private String address;
    private Set<Animal> animals;

    public Owner(String name, String address, Set<Animal> animals) {
        this.name = name;
        this.address = address;
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Właściciel: ").append(name)
          .append(", Adres: ").append(address).append("\n");
        if (animals.isEmpty()) {
            sb.append("  Brak zwierząt\n");
        } else {
            animals.forEach(a -> sb.append("  - ").append(a).append("\n"));
        }
        return sb.toString();
    }
}