package com.coredesk.service;

import com.coredesk.dto.TaskRequestDto;
import com.coredesk.dto.TaskResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class TaskService {

    // Тимчасове сховище для завдань в оперативній пам'яті (потокобезпечне)
    private final List<TaskResponseDto> tasks = new CopyOnWriteArrayList<>();

    public TaskResponseDto createTask(TaskRequestDto request) {
        // Генеруємо унікальний ID та створюємо об'єкт відповіді
        TaskResponseDto newTask = new TaskResponseDto(
                UUID.randomUUID().toString(),
                request.getTitle(),
                request.getDescription(),
                "pending", // початковий статус завдання
                request.getDeadline(),
                request.getLink(),
                LocalDateTime.now() // час створення
        );

        tasks.add(newTask);
        return newTask;
    }

    public List<TaskResponseDto> getTasksForToday() {
        LocalDate today = LocalDate.now();

        // Фільтруємо завдання, де дата дедлайну збігається з сьогоднішнім днем
        return tasks.stream()
                .filter(task -> task.getDeadline().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
    }
}