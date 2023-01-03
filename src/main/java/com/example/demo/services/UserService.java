package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repostitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return (List<User>) this.userRepository.findAll();
    }
    
    public User getById(Integer userId) {
        return this.userRepository.findById(userId).orElse(null);
    }
    
    public User getByName(String name) {
        return this.userRepository.findByName(name).orElse(null);
    }
    
    public User create(User user) {
        if (user.getId() != null) {
            throw new RuntimeException("Cannot create a new User. This User already has an ID.");
        }
        User foundByName = this.getByName(user.getName());

        if (foundByName != null) {
            throw new RuntimeException(String.format("Cannot create a new User. Name: '%s' is already in use.", user.getName()));
        }
        return this.userRepository.save(user);
    }
    
    public User update(User user) {
        User foundById = this.getById(user.getId());
        
        if (foundById == null) {
            throw new RuntimeException(String.format("Cannot update User. User with ID: '%d' does not exist.", user.getId()));
        }
        User foundByName = this.getByName(user.getName());
        
        if (foundByName != null && !foundByName.getId().equals(user.getId())) {
            throw new RuntimeException(String.format("Cannot update User. Name: '%s' is already in use.", user.getName()));
        }
        return this.userRepository.save(user);
    }
    
    public void deleteById(Integer userId) {
        this.userRepository.deleteById(userId);
    }
}
