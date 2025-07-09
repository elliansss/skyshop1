package org.skypro.skyshop1.model.product;

import java.util.UUID;

public class Fruit extends Product {
    public Fruit(String name, int price, boolean isSpecial, UUID id) {
        super(name, price, isSpecial, id);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", isSpecial=" + isSpecial() +
                ", id=" + getId() +
                '}';
    }
}