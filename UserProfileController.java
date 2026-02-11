package com.example.bonus_userprofile_api.controller;

import com.example.bonus_userprofile_api.model.UserProfile;
import com.example.bonus_userprofile_api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {

        users.add(new UserProfile(1L, "john_doe", "john@email.com",
                "John Doe", 25, "USA",
                "Software Developer", true));

        users.add(new UserProfile(2L, "mary_smith", "mary@email.com",
                "Mary Smith", 30, "UK",
                "Data Analyst", true));
    }

    // GET all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Users retrieved successfully", users)
        );
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long id) {

        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User found", user)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    // Search by username
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(
            @RequestParam String username) {

        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getUsername().toLowerCase()
                    .contains(username.toLowerCase())) {
                result.add(user);
            }
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search completed", result)
        );
    }

    // Search by country
    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(
            @PathVariable String country) {

        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Users filtered by country", result)
        );
    }

    // Search by age range
    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {

        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Users filtered by age range", result)
        );
    }

    // POST - Create user
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(
            @RequestBody UserProfile user) {

        users.add(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,
                        "User profile created successfully", user));
    }

    // PUT - Update user
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(
            @PathVariable Long id,
            @RequestBody UserProfile updatedUser) {

        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {

                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAge(updatedUser.getAge());
                user.setCountry(updatedUser.getCountry());
                user.setBio(updatedUser.getBio());
                user.setActive(updatedUser.isActive());

                return ResponseEntity.ok(
                        new ApiResponse<>(true,
                                "User updated successfully", user));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false,
                        "User not found", null));
    }

    // PATCH - Activate/Deactivate
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<UserProfile>> toggleStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {
                user.setActive(active);

                return ResponseEntity.ok(
                        new ApiResponse<>(true,
                                "User status updated", user));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false,
                        "User not found", null));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {

        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {
                users.remove(user);
                return ResponseEntity.ok(
                        new ApiResponse<>(true,
                                "User deleted successfully", null));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false,
                        "User not found", null));
    }
}
