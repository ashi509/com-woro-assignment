package com.woro.assignment.controller;

import com.woro.assignment.entity.ToDoItem;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todoItem")
public class ToDoItemController {
    @Autowired
    private ToDoItemService toDoItemService;
    @PostMapping("/{userId}")
    ResponseEntity<?> addNewToDoItem(@RequestBody ToDoItem toDoItem,@PathVariable("userId") int userId) throws CustomException{
        return  toDoItemService.addNewToDoItem(toDoItem,userId);
    }
    @GetMapping("/{id}/{todoListId}/{userid}")
    ResponseEntity<?> getSpecificToDoItem(@PathVariable int id,@PathVariable("todoListId") int todoListId,@PathVariable("userId") int userId) throws CustomException{
        return toDoItemService.getSpecificToDoItem(id,todoListId,userId);
    }
    @GetMapping("/{todoListId}/{userId}")
    ResponseEntity<?> getAllToDoItems(@PathVariable("todoListId") int todoListId,@PathVariable("userId") int userId) throws CustomException{
        return toDoItemService.getAllToDoItems(todoListId,userId);
    }
    @PutMapping("/{id}/{todoListId}/{userId}")
    ResponseEntity<?> updateToDoItem(@RequestBody ToDoItem toDoItem,@PathVariable int id,@PathVariable("todoListId") int todoListId,@PathVariable("userId") int userId) throws CustomException{
        return toDoItemService.updateToDoItem(toDoItem,id,todoListId,userId);
    }
    @DeleteMapping("/{id}/{todoListId}/{userId}")
    ResponseEntity<?> deleteToDoItem(@PathVariable int id,@PathVariable("todoListId") int todoListId,@PathVariable("UserId") int userId) throws CustomException{
        return toDoItemService.deleteToDoItem(id,todoListId,userId);
  }
}
