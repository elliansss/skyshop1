package org.skypro.skyshop1.controller;

import org.skypro.skyshop1.model.article.Article;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.search.SearchResult;
import org.skypro.skyshop1.service.SearchService;
import org.skypro.skyshop1.model.basket.ProductBasket;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.service.StorageService;
import org.skypro.skyshop1.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    @Autowired
    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        try {
            basketService.addProductToBasket(id);
            return "Продукт успешно добавлен ";
        } catch (IllegalArgumentException e) {
            return "Ошибка " + e.getMessage();
        }
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}