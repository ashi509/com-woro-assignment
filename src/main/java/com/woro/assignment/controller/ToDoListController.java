package com.woro.assignment.controller;

import com.woro.assignment.entity.ToDoList;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;
    @PostMapping("/{userId}")
    ResponseEntity<?> addNewToDoList(@RequestBody ToDoList toDoList,@PathVariable("userId") int userId)throws CustomException{
        return toDoListService.addNewToDoList(toDoList,userId);
    }
    @GetMapping("/{id}/{userId}")
    ResponseEntity<?> getSpecificToDoList(@PathVariable int id, @PathVariable("userId") int userId)throws CustomException{
        return toDoListService.getSpecificToDoList(id,userId);
    }
    @GetMapping("/{userId}")
    ResponseEntity<?> getAllToDoLists(@PathVariable int userId)throws CustomException{
        return toDoListService.getAllToDoLists(userId);
    }
    @PutMapping("/{id}/{userId}")
    ResponseEntity<?> updateToDoList(@RequestBody ToDoList toDoList,@PathVariable int id,@PathVariable("userId")int userId)throws CustomException{
        return toDoListService.updateToDoList(toDoList,id,userId);
    }
    @DeleteMapping("/{id}/{userId}")
    ResponseEntity<?> deleteSpecificToDoList(@PathVariable int id,@PathVariable("userId")int userId)throws CustomException{
        return toDoListService.deleteSpecificToDoList(id,userId);
    }
}
