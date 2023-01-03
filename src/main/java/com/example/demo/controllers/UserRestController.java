package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return this.service.getAll();
    }
    
    @GetMapping("/{userId}")
    public User getById(@PathVariable Integer userId) {
        return this.service.getById(userId);
    }
    
    @PostMapping
    public User create(@RequestBody ObjectNode requestBody) {
        User user = parseJsonToUser(requestBody);

        return this.service.create(user);
    }
    
    @PutMapping("/{userId}")
    public User update(@PathVariable Integer userId, @RequestBody ObjectNode requestBody) {        
        User user = parseJsonToUser(requestBody);
        
        if (user.getId() == null) {
            throw new RuntimeException("Cannot update User. ID is not provided.");
        }
        if (!Objects.equals(userId, user.getId())) {
            throw new RuntimeException(String.format("Cannot update User on path: '%d', with data from User with ID: '%d'.", userId, user.getId()));
        }
        return this.service.update(user);
    }
    
    @DeleteMapping("{userId}")
    public void deleteById(@PathVariable Integer userId) {
        this.service.deleteById(userId);
    }
    
    private User parseJsonToUser(ObjectNode json) {
        User user = new User();

        String name = json.get("name").asText(null);
        Integer userId = json.has("id") ? json.get("id").asInt() : null;
        
        user.setName(name);
        user.setId(userId);
        
        return user;
    }
}
