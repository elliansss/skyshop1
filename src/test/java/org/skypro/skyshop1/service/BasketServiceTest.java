package org.skypro.skyshop1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop1.model.basket.ProductBasket;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.exception.NoSuchProductException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void testAddProductToBasket_NonExistingProduct_ThrowsException() {
        UUID productId = UUID.randomUUID();
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(productId));
        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void testAddProductToBasket_ExistingProduct_CallsProductBasketAddProduct() {
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(productId);

        verify(productBasket).addProduct(productId);
    }

    @Test
    void testGetUserBasket_EmptyProductBasket_ReturnsEmptyUserBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0.0, userBasket.getTotal());
    }

    @Test
    void testGetUserBasket_ProductInBasketDoesNotExistInStorage_ThrowsException() {
        UUID productId = UUID.randomUUID();
        Map<UUID, Integer> productsInBasket = Collections.singletonMap(productId, 1);
        when(productBasket.getProducts()).thenReturn(productsInBasket);
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.getUserBasket());
    }

}