package com.woro.assignment.controller;

import com.woro.assignment.entity.User;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    ResponseEntity<?> createNewUser(@RequestBody User user) throws CustomException{
        return userService.createNewUser(user);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) throws CustomException{
        return  userService.updateUser(user,id);
    }
    @GetMapping
    ResponseEntity<?> getAllUsers() throws CustomException{
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getSpecificUser(@PathVariable int id) throws CustomException{
        return userService.getSpecificUser(id);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUsers(@PathVariable int id) throws CustomException{
        return userService.deleteUser(id);
    }
}
