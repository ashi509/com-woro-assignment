package com.woro.assignment.service.impl;

import com.woro.assignment.entity.User;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.repository.UserRepository;
import com.woro.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public ResponseEntity<?> createNewUser(User user) throws CustomException {
        user.setId(0);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        var users=userRepository.save(user);
        if(users==null)
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @Override
    public ResponseEntity<?> updateUser(User user, int id) throws CustomException {
        var users=userRepository.findById(id).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND.value(),
                "User not found with given id "+id));
        user.setId(id);
        user.setPassword(users.getPassword());
        users=userRepository.save(user);
        if(users==null)
            throw new CustomException(HttpStatus.NOT_MODIFIED.value(),"User does not update");
        return ResponseEntity.ok(users);
    }
    @Override
    public ResponseEntity<?> getAllUsers() throws CustomException {
        var users=userRepository.findAll();
        if(users==null)
            throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Users not found.");
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> getSpecificUser(int id) throws CustomException {
        var user=userRepository.findById(id).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND.value(),
                "user not found with given id "+id));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) throws CustomException {
        var user=userRepository.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND.value(),
                "user not found with given id "+id));
        userRepository.deleteById(id);
    return ResponseEntity.ok(user);
    }
}
