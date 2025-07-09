package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.article.Article;
import org.skypro.skyshop1.model.product.Fruit;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.product.Vegetables;
import org.skypro.skyshop1.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        populateStorages();
    }

    private void populateStorages() {
        // Добавили тестовые продукты с UUID
        UUID pearId = UUID.randomUUID();
        Product pear = new Fruit("Груша", 70, true, pearId);
        UUID grapeId = UUID.randomUUID();
        Product grape = new Fruit("Виноград", 190, true, grapeId);
        UUID mangoId = UUID.randomUUID();
        Product mango = new Fruit("Манго", 300, false, mangoId);
        UUID appleId = UUID.randomUUID();
        Product apple = new Fruit("Яблоко", 80, false, appleId);
        UUID strawberryId = UUID.randomUUID();
        Product strawberry = new Fruit("Клубника", 250, false, strawberryId);
        UUID cucumberId = UUID.randomUUID();
        Product cucumber = new Vegetables("Огурец", 160, false, cucumberId);

        // Добавляем продукты в хранилище

        productStorage.put(pear.getId(), pear);
        productStorage.put(grape.getId(), grape);
        productStorage.put(mango.getId(), mango);
        productStorage.put(apple.getId(), apple);
        productStorage.put(strawberry.getId(), strawberry);
        productStorage.put(cucumber.getId(), cucumber);

        // Создаем тестовые статьи с UUID
        Article article1 = new Article("Как понять, что манго сладкое?", "Лучшие манго этого сезона", UUID.randomUUID());
        Article article2 = new Article("Груша - чем полезна?", "Одна груша - доктор не нужен", UUID.randomUUID());

        // Добавляем статьи в хранилище
        articleStorage.put(article1.getId(), article1);
        articleStorage.put(article2.getId(), article2);
    }


    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }


    public void addProduct(Product product) {
        productStorage.put(product.getId(), product);
    }

    public void addArticle(Article article) {
        articleStorage.put(article.getId(), article);
    }

    public Product getProductById(UUID id) {
        return productStorage.get(id);
    }

    public Article getArticleById(UUID id) {
        return articleStorage.get(id);
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(productStorage.values());
        searchables.addAll((Collection<? extends Searchable>) articleStorage.values());
        return searchables;
    }
}