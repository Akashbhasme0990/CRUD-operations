package com.CRUD.Operations.Service;

import com.CRUD.Operations.Entity.User;
import com.CRUD.Operations.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User updateUser(int userId, User userDetails) {
        logger.info("Updating user with ID: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUserName(userDetails.getUserName());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhone(userDetails.getPhone());
            User updatedUser = userRepository.save(existingUser);
            logger.info("User updated successfully: {}", updatedUser);
            return updatedUser;
        } else {
            logger.error("User not found with id: {}", userId);
            throw new RuntimeException("User not found with id " + userId);
        }
    }
    public Map<User, ArrayList<String>> getUserData() {
        Map<User, ArrayList<String>> hashMap = new HashMap<>();

        List<User> users = userRepository.findAll();

        List<User> filteredUsers = users.stream()
                .filter(user -> user.getUserName().length() > 5)
                .toList();

        for (User user : filteredUsers) {
            ArrayList<String> userNames = new ArrayList<>();
            userNames.add(user.getUserName());
            hashMap.put(user, userNames);
        }

        return hashMap;
    }

}
