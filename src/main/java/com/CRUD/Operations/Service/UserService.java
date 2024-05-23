package com.CRUD.Operations.Service;

import com.CRUD.Operations.Entity.User;
import com.CRUD.Operations.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
