package com.example.question4_ecommerce_api.controller;

import com.example.question4_ecommerce_api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        // Sample products
        products.add(new Product(1L, "iPhone 15", "Latest Apple phone", 1200.0, "Electronics", 5, "Apple"));
        products.add(new Product(2L, "Galaxy S23", "Samsung flagship phone", 1100.0, "Electronics", 10, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "Apple laptop", 2500.0, "Electronics", 3, "Apple"));
        products.add(new Product(4L, "Dell XPS 13", "Dell laptop", 1800.0, "Electronics", 7, "Dell"));
        products.add(new Product(5L, "Nike Air Max", "Running shoes", 150.0, "Footwear", 20, "Nike"));
        products.add(new Product(6L, "Adidas Ultraboost", "Comfort shoes", 160.0, "Footwear", 15, "Adidas"));
        products.add(new Product(7L, "Levi's Jeans", "Blue denim jeans", 60.0, "Clothing", 50, "Levi's"));
        products.add(new Product(8L, "Sony Headphones", "Noise-cancelling", 200.0, "Electronics", 8, "Sony"));
        products.add(new Product(9L, "Coffee Maker", "Automatic coffee machine", 120.0, "Home Appliances", 12, "Philips"));
        products.add(new Product(10L, "Instant Pot", "Pressure cooker", 90.0, "Home Appliances", 18, "Instant Pot"));
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {

        if (page != null && limit != null) {
            int start = (page - 1) * limit;
            int end = Math.min(start + limit, products.size());
            if (start >= products.size()) {
                return ResponseEntity.ok(new ArrayList<>()); // empty list
            }
            return ResponseEntity.ok(products.subList(start, end));
        }

        return ResponseEntity.ok(products);
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // GET products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET products by keyword (name or description)
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET products in stock
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getProductsInStock() {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }

    // POST - Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // PUT - Update product details
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product updatedProduct) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setName(updatedProduct.getName());
                p.setDescription(updatedProduct.getDescription());
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setStockQuantity(updatedProduct.getStockQuantity());
                p.setBrand(updatedProduct.getBrand());
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // PATCH - Update stock quantity
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId,
                                               @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE - Remove product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                products.remove(p);
                return ResponseEntity.noContent().build(); // 204
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
