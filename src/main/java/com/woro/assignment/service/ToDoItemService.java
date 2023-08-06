package com.woro.assignment.service;

import com.woro.assignment.entity.ToDoItem;
import com.woro.assignment.exception.CustomException;
import org.springframework.http.ResponseEntity;

public interface ToDoItemService {
    ResponseEntity<?> addNewToDoItem(ToDoItem toDoItem,int userId) throws CustomException;
    ResponseEntity<?> getSpecificToDoItem(int id,int todoListId,int userId) throws CustomException;
    ResponseEntity<?> getAllToDoItems(int todoListId,int userId) throws CustomException;
    ResponseEntity<?> updateToDoItem(ToDoItem toDoItem,int id,int todoListId,int userId) throws CustomException;
    ResponseEntity<?> deleteToDoItem(int id,int todoListId,int userId) throws CustomException;
    }
