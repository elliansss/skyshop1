package org.skypro.skyshop1.model.product;

import java.util.UUID;

public class Vegetables extends Product {
    public Vegetables(String name, int price, boolean isSpecial, UUID id) {
        super(name, price, isSpecial, id);
    }

    @Override
    public String toString() {
        return "Vegetables{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", isSpecial=" + isSpecial() +
                ", id=" + getId() +
                '}';
    }
}
