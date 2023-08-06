package com.woro.assignment.service.impl;

import com.woro.assignment.entity.ToDoItem;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.repository.ToDoItemRepository;
import com.woro.assignment.repository.ToDoListRepository;
import com.woro.assignment.repository.UserRepository;
import com.woro.assignment.service.ToDoItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService {
    private ToDoItemRepository toDoItemRepository;
    private ToDoListRepository toDoListRepository;
    private UserRepository userRepository;
    @Override
    public ResponseEntity<?> addNewToDoItem(ToDoItem toDoItem,int userId) throws CustomException {
        ToDoItem finalToDoItem = toDoItem;
        var todoList= toDoListRepository.findAll().parallelStream()
                        .filter(toDoList -> toDoList.getUser().getId()==userId && toDoList.getId()== finalToDoItem.getToDoList().getId())
                                .collect(Collectors.toList());
        if(todoList.size()==0)
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo list not found with given id "+toDoItem.getToDoList().getId());
        var todo=todoList.get(0);
        toDoItem.setToDoList(todo);
        toDoItem.setId(0);
        toDoItem=toDoItemRepository.save(toDoItem);
        if(toDoItem==null)
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Something went wrong to do item not added.");
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoItem);
    }
    @Override
    public ResponseEntity<?> getSpecificToDoItem(int id,int todoListId,int userId) throws CustomException {
        var toDoItems= toDoItemRepository.findAll().parallelStream()
                .filter(toDoItem -> toDoItem.getToDoList().getId()==todoListId && toDoItem.getToDoList().getUser().getId()==userId
                && toDoItem.getId()==id)
                .collect(Collectors.toList());
        if(toDoItems.size()==0)
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo item not found");
        return ResponseEntity.ok(toDoItems.get(0));
    }
    @Override
    public ResponseEntity<?> getAllToDoItems(int todoListId,int userId) throws CustomException {
        var todoItems= toDoItemRepository.findByToDoList(toDoListRepository.findById(todoListId)
                .orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND.value(), "Todo list not found"))).get();
        var items= todoItems.parallelStream().filter(toDoItem -> toDoItem.getToDoList().getUser().getId()==userId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }
    @Override
    public ResponseEntity<?> updateToDoItem(ToDoItem toDoItem, int id,int todoListId,int userId) throws CustomException {
        var todo=toDoItemRepository.findById(id).orElseThrow(()->new
                CustomException(HttpStatus.NOT_FOUND.value(), "To do item not found with given id "+id));
        if(!(todo.getToDoList().getId()==todoListId && todo.getToDoList().getUser().getId()==userId))
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo item not found");
        toDoItem.setId(id);
        toDoItem.setToDoList(todo.getToDoList());
        toDoItem.setDueDate(todo.getDueDate());
        toDoItem=toDoItemRepository.save(toDoItem);
        if(toDoItem==null)
            throw new CustomException(HttpStatus.EXPECTATION_FAILED.value(),
                    "To do item not update. ");
        return ResponseEntity.status(HttpStatus.OK).body(toDoItem);
    }
    @Override
    public ResponseEntity<?> deleteToDoItem(int id,int todoListId,int userId) throws CustomException {
        var todo=toDoItemRepository.findById(id).orElseThrow(()->new
                CustomException(HttpStatus.NOT_FOUND.value(), "To do item not found with given id "+id));
        if(!(todo.getToDoList().getId()==todoListId && todo.getToDoList().getUser().getId()==userId))
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo item not found");
        toDoItemRepository.deleteById(id);
        return ResponseEntity.ok(todo);
    }
}
