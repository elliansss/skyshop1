package org.skypro.skyshop1.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop1.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable, Comparable<Product> {
    private final String name;
    public static final String PRODUCT = "PRODUCT";
    private final int price;
    private final boolean isSpecial;
    private final UUID id;


    public Product(String name, int price, boolean isSpecial, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Нет имени продукта ");
        }
        this.name = name;
        this.price = price;
        this.isSpecial = isSpecial;
        this.id = Objects.requireNonNull(id, "Id can`t be null");
    }

    public Product(String name, int price, UUID id) {
        this(name, price, false, id);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Product other) {
        if (other == null) return -1;
        return this.name.compareTo(other.name);
    }


    public boolean isSpecial() {
        return isSpecial;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return PRODUCT;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public abstract String toString();
}