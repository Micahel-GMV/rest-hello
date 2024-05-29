package com.example.resthello.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    public List<User> getAllUsernames() {
        return userRepository.findAll();
    }
}