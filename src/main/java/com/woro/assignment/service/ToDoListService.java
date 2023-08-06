package com.woro.assignment.service;

import com.woro.assignment.entity.ToDoList;
import com.woro.assignment.exception.CustomException;
import org.springframework.http.ResponseEntity;

public interface ToDoListService {
    ResponseEntity<?> addNewToDoList(ToDoList toDoList,int userId)throws CustomException;
    ResponseEntity<?> getSpecificToDoList(int id,int userId)throws CustomException;
    ResponseEntity<?> getAllToDoLists(int userId)throws CustomException;
    ResponseEntity<?> updateToDoList(ToDoList toDoList,int id,int userId)throws CustomException;
    ResponseEntity<?> deleteSpecificToDoList(int id,int userId)throws CustomException;
}
