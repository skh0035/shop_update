package com.example.shop.Services;

import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUserId(Long id){
       return userRepository.findById(id).orElse(null);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void updateUser(Long id, User updatedUser, String role) {
        User existingUser = findByUserId(id);
        existingUser.setRole(role);
        userRepository.save(existingUser);
    }

}
