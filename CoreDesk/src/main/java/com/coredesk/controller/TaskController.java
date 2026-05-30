package com.coredesk.controller;

import com.coredesk.dto.TaskRequestDto;
import com.coredesk.dto.TaskResponseDto;
import com.coredesk.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks", description = "Управління навчальними завданнями та дедлайнами")
public class TaskController {

    private final TaskService taskService;

    // Впровадження залежності через конструктор (Constructor Injection)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Створити нове завдання", description = "Додає новий дедлайн у систему з валідацією дати")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto requestDto) {
        TaskResponseDto createdTask = taskService.createTask(requestDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/today")
    @Operation(summary = "Отримати завдання на сьогодні", description = "Повертає список усіх завдань, дедлайн яких закінчується сьогодні")
    public ResponseEntity<List<TaskResponseDto>> getTasksForToday() {
        List<TaskResponseDto> tasks = taskService.getTasksForToday();
        return ResponseEntity.ok(tasks);
    }
}