package com.example.shop.Services;

import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUserId(Long id){
       return userRepository.findById(id).orElse(null);
    }


}
