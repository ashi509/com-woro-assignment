package com.woro.assignment.repository;

import com.woro.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(nativeQuery = true,value = "select * from user where username = :uname")
    Optional<User> findUserByUsername(@Param("uname") String username);
}
