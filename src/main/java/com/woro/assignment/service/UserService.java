package com.woro.assignment.service;

import com.woro.assignment.entity.User;
import com.woro.assignment.exception.CustomException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createNewUser(User user) throws CustomException;
    ResponseEntity<?> updateUser(User user, int id) throws CustomException;
    ResponseEntity<?> getAllUsers() throws CustomException;
    ResponseEntity<?> getSpecificUser(int id) throws CustomException;
    ResponseEntity<?> deleteUser(int id) throws CustomException;
}
