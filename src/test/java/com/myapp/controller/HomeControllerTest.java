package com.myapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private HomeController homeController;

    @BeforeEach()
    void setUp() {
        homeController = new HomeController();
    }

    @Test
    void sayHelloTo_GivenName_ShouldReturnGreetingMessage() {
        var result = homeController.sayHelloTo("John");
        assertEquals("Hello John!", result);
    }

    @Test
    void getProducts_ShouldReturnListOfProducts() {
        var result = homeController.getProducts();
        assertEquals(2, result.size());
    }
}