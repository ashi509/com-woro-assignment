package com.woro.assignment.service.impl;

import com.woro.assignment.entity.ToDoList;
import com.woro.assignment.exception.CustomException;
import com.woro.assignment.repository.ToDoListRepository;
import com.woro.assignment.repository.UserRepository;
import com.woro.assignment.service.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {
    private ToDoListRepository toDoListRepository;
    private UserRepository userRepository;
    @Override
    public ResponseEntity<?> addNewToDoList(ToDoList toDoList,int userId) throws CustomException {
        toDoList.setId(0);
        toDoList.setUser(userRepository.findById(userId).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND.value(), "User does not exists")));
        var todo= toDoListRepository.save(toDoList);
        if(todo==null)
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @Override
    public ResponseEntity<?> getSpecificToDoList(int id,int userId) throws CustomException {
        var todoList=toDoListRepository.findAll().parallelStream()
                .filter(toDoList -> toDoList.getUser().getId()==userId).collect(Collectors.toList());
        var specificTodoList= todoList.parallelStream().filter(toDoList -> toDoList.getId()==id).collect(Collectors.toList());
        if(specificTodoList.size()==0)
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo list not found with given id "+id);
        return ResponseEntity.ok(specificTodoList.get(0));
    }

    @Override
    public ResponseEntity<?> getAllToDoLists(int userId) throws CustomException {
        return ResponseEntity.ok(toDoListRepository.findAll().parallelStream()
                .filter(toDoList -> toDoList.getUser().getId()==userId).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> updateToDoList(ToDoList toDoList, int id,int userId) throws CustomException {
        var todoList= toDoListRepository.findAll().parallelStream()
                .filter(toDoList1 -> toDoList1.getUser().getId()==userId && toDoList1.getId()==id)
                .collect(Collectors.toList());
        if(todoList.size()==0)
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo list not found with given id "+id);
        var todo= todoList.get(0);
        todo.setId(id);
        todo.setName(toDoList.getName());
        todo=toDoListRepository.save(todo);
        if(todo==null)
            throw new CustomException(HttpStatus.NO_CONTENT.value(),
                    "Something went wrong to list is not update");
        return ResponseEntity.ok(todo);
    }

    @Override
    public ResponseEntity<?> deleteSpecificToDoList(int id,int userId) throws CustomException {
        var todoList= toDoListRepository.findAll().parallelStream()
                .filter(toDoList1 -> toDoList1.getUser().getId()==userId && toDoList1.getId()==id)
                .collect(Collectors.toList());
        if(todoList.size()==0)
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Todo list not found with given id "+id);
        toDoListRepository.deleteById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(todoList.get(0));
    }
}
