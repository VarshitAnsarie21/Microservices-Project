package com.microservice.user.service;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.model.User;
import com.microservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        User newUser = userRepository.findById(user.getUserId()).orElse(new User());
        if(newUser.getUserId() != null) {
            throw new RuntimeException("User already exists");
        }
        newUser.setUserName(user.getUserName());
        newUser.setUserEmail(user.getUserEmail());
        return userRepository.save(newUser);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exists"));

        if (userDTO.getUserName() != null) {
            existingUser.setUserName(userDTO.getUserName());
        }
        if (userDTO.getUserEmail() != null) {
            existingUser.setUserEmail(userDTO.getUserEmail());
        }

        return userRepository.save(existingUser);
    }
}

