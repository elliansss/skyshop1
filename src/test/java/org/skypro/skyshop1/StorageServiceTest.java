package org.skypro.skyshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop1.model.article.Article;
import org.skypro.skyshop1.model.product.Fruit;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.exception.NoSuchProductException;
import org.skypro.skyshop1.service.StorageService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    void setUp() {
        storageService = new StorageService();
    }

    @Test
    void testGetProductById_ExistingProduct_ReturnsProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new Fruit("Test Fruit", 100, false, productId);
        storageService.addProduct(product);

        Product retrievedProduct = storageService.getProductById(productId)
                .orElseThrow(() -> new NoSuchProductException());

        assertEquals(product, retrievedProduct);
    }

    @Test
    void testGetProductById_NonExistingProduct_ReturnsEmptyOptional() {
        UUID productId = UUID.randomUUID();

        assertTrue(storageService.getProductById(productId).isEmpty());
    }

    @Test
    void testAddProduct_ProductIsAdded() {
        UUID productId = UUID.randomUUID();
        Product product = new Fruit("Test Fruit", 100, false, productId);

        storageService.addProduct(product);

        assertFalse(storageService.getAllProducts().isEmpty());
        assertTrue(storageService.getProductById(productId).isPresent());
        assertEquals(product, storageService.getProductById(productId).get());
    }

    @Test
    void testAddArticle_ArticleIsAdded() {
        UUID articleId = UUID.randomUUID();
        Article article = new Article("Test Article", "Test Content", articleId);

        storageService.addArticle(article);

        assertFalse(storageService.getAllArticles().isEmpty());
        boolean articleFound = storageService.getAllArticles().stream()
                .anyMatch(a -> a.getId().equals(articleId));
        assertTrue(articleFound);
    }
}