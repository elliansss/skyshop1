package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.basket.BasketItem;
import org.skypro.skyshop1.model.basket.ProductBasket;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isPresent()) {
            productBasket.addProduct(id);
        } else {
            throw new IllegalArgumentException("Продукт с id" + id + "Не найден");
        }
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsInBasket = productBasket.getProducts();
        List<BasketItem>  basketItems = productsInBasket.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new IllegalStateException("Продукт с id " + productId + " не найден в StorageService, но найден в ProductBasket"));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}

