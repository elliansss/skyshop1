package org.skypro.skyshop1.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Такого продукта нет");
    }
}
