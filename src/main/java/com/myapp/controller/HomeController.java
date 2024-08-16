package com.myapp.controller;

import com.myapp.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("hello/{name}")
    public String sayHelloTo(@PathVariable String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return List.of(new Product("Sladoled", 100), new Product("Krem bananica", 100));
    }
}
