package com.example.question3_restaurant_api.controller;

import com.example.question3_restaurant_api.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        // Sample menu items
        menu.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable rolls", 5.0, "Appetizer", true));
        menu.add(new MenuItem(2L, "Caesar Salad", "Fresh salad with dressing", 6.5, "Appetizer", true));
        menu.add(new MenuItem(3L, "Grilled Chicken", "Served with vegetables", 12.0, "Main Course", true));
        menu.add(new MenuItem(4L, "Spaghetti Bolognese", "Classic Italian pasta", 11.0, "Main Course", false));
        menu.add(new MenuItem(5L, "Cheesecake", "Creamy dessert", 6.0, "Dessert", true));
        menu.add(new MenuItem(6L, "Chocolate Brownie", "Rich chocolate brownie", 5.5, "Dessert", true));
        menu.add(new MenuItem(7L, "Coke", "Soft drink", 2.0, "Beverage", true));
        menu.add(new MenuItem(8L, "Coffee", "Hot brewed coffee", 3.0, "Beverage", false));
    }

    // GET all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenu() {
        return ResponseEntity.ok(menu);
    }

    // GET menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // GET menu items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET only available items (?available=true)
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenu(@RequestParam boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    // Search by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    // POST - Add new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
        menu.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // PUT - Toggle availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menu.removeIf(item -> item.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
