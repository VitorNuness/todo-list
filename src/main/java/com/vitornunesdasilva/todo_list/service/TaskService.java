package com.vitornunesdasilva.todo_list.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vitornunesdasilva.todo_list.entity.Task;
import com.vitornunesdasilva.todo_list.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    List<Task> list() {
        Sort sort = Sort.by(Direction.DESC, "isComplete")
                .and(Sort.by(Direction.ASC, "date"))
                .and(Sort.by(Direction.DESC, "priority")
                        .and(Sort.by(Direction.ASC, "id")));
        return taskRepository.findAll(sort);
    }

    List<Task> create(Task task) {
        taskRepository.save(task);
        return list();
    }

    List<Task> update(Task task) {
        taskRepository.save(task);
        return list();
    }
}
