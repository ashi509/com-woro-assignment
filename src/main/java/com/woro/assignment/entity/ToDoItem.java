package com.woro.assignment.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ToDoList toDoList;
    private boolean completed;
    private Date dueDate;
}
