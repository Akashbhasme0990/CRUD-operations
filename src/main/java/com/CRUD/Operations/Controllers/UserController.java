package com.CRUD.Operations.Controllers;

import com.CRUD.Operations.Entity.User;
import com.CRUD.Operations.Repository.UserRepository;
import com.CRUD.Operations.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping("/Users")
    @Operation(summary = "Gets all the users",
            description= "Users must exist")
    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
    @PostMapping("/User")
    public String createUser(@RequestBody User user){
        try {
            userRepository.save(user);
            return "User saved sucessfully";
        } catch (Exception e) {
            return "Error saving user: " + e.getMessage();
        }

    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int userId){
        try{
            userRepository.deleteById(userId);
            return "User deleted successfully!";
        }catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/yugal")
    public  String getDetailsOfStudent(){
        return "my name is yugal";
    }

}
