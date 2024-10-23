package com.vitornunesdasilva.todo_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitornunesdasilva.todo_list.entity.Task;
import com.vitornunesdasilva.todo_list.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    List<Task> create(@RequestBody @Valid Task task) {
        return taskService.create(task);
    }

    @GetMapping()
    List<Task> list() {
        return taskService.list();
    }
}
