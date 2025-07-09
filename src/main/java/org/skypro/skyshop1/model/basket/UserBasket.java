package org.skypro.skyshop1.model.basket;

import org.skypro.skyshop1.model.product.Product;

import java.util.List;

public final class UserBasket {

    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items) {
        this.items = List.copyOf(items);
        this.total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}