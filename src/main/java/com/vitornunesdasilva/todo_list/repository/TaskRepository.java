package com.vitornunesdasilva.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitornunesdasilva.todo_list.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
