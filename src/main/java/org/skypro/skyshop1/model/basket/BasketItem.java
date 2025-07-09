package org.skypro.skyshop1.model.basket;

import org.skypro.skyshop1.model.product.Product;

public class BasketItem {

    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
