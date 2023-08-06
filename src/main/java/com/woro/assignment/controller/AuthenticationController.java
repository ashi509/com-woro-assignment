package com.woro.assignment.controller;

import com.woro.assignment.entity.AuthReqDTO;
import com.woro.assignment.entity.AuthResDTO;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.repository.UserRepository;
import com.woro.assignment.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthReqDTO authReqDTO) throws CustomException {
        if(authReqDTO.getUsername() == null || authReqDTO.getPassword()==null)
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad credentials");
        System.out.println("Trsg---");
        System.out.println(authReqDTO);
        var user=userRepository.findUserByUsername(authReqDTO.getUsername())
                .orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad credentials rt"));
        System.out.println(user);
        if(!bCryptPasswordEncoder.matches(authReqDTO.getPassword(), user.getPassword()))
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad credentials");
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResDTO(jwtUtil.generateToken(user.getEmail(), user.getUsername())));
    }
}
