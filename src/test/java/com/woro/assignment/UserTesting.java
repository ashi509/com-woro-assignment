package com.woro.assignment;

import com.woro.assignment.entity.User;
import com.woro.assignment.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTesting {
    @Autowired
    private UserRepository userRepository;

    /**
     * For new user adding testing
     */
    @Test
    @Order(1)
    @Rollback(value = false)
    public void  testCreateUser(){
        User user= User.builder()
        .username("Raju")
        .password("Ashish@123")
        .email("ashish@123gmail.com")
                .build();
        userRepository.save(user);
      assertThat(user.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void testGetUsers(){
        List<User> list=userRepository.findAll();
        assertThat(list.size()).isGreaterThan(0);

    }
    @Test
    @Order(3)
    public void testSingleUser() {
        User user = userRepository.findById(6).get();
        assertThat(user.getId()).isEqualTo(6);
    }
    @Test
    @Order(4)
    @Rollback(value = false)
    public void testUpdateUser(){
        var users=userRepository.findById(6).get();
        users.setUsername("Ashish");
        User userUpdated=userRepository.save(users);
        assertThat(userUpdated.getUsername()).isEqualTo("Ashish");


    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void testDeleteUser() {
        User user = userRepository.findById(6).get();
        userRepository.delete(user);
        User user1 = null;
        Optional<User> optionalUser = userRepository.findUserByUsername("Ashish");
        if (optionalUser.isPresent()) {
            user1 = optionalUser.get();
        }
        assertThat(user1).isNull();
    }
}
