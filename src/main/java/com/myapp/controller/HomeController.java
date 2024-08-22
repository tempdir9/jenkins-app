package com.myapp.controller;

import com.myapp.domain.Product;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class.getName());

    @GetMapping("hello/{name}")
    public String sayHelloTo(@PathVariable String name) {
        LOG.info(String.format("Saying hello to %s!", name));
        return String.format("Hello %s!", name);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        LOG.info("Getting products");
        return List.of(new Product("Sladoled", 100), new Product("Krem bananica", 100));
    }

    @GetMapping("os")
    public String getOsInfo() {
        LOG.info("Getting OS info");
        return System.getProperty("os.name");
    }
}
