package com.woro.assignment.repository;

import com.woro.assignment.entity.ToDoItem;
import com.woro.assignment.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem,Integer> {
    Optional<List<ToDoItem>> findByToDoList(ToDoList toDoList);
}
