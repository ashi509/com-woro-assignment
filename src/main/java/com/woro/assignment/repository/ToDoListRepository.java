package com.woro.assignment.repository;

import com.woro.assignment.entity.ToDoList;
import com.woro.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList,Integer> {
    Optional<List<ToDoList>> findByUser(User user);
}
