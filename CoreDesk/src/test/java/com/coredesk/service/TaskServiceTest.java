package com.coredesk.service;

import com.coredesk.dto.TaskRequestDto;
import com.coredesk.dto.TaskResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldCreateTaskSuccessfully() {
        // Given
        TaskRequestDto request = new TaskRequestDto();
        request.setTitle("Тестовий дедлайн");
        request.setDescription("Опис");
        request.setDeadline(LocalDateTime.now().plusDays(1));

        // When
        TaskResponseDto response = taskService.createTask(request);

        // Then
        assertNotNull(response.getId());
        assertEquals("Тестовий дедлайн", response.getTitle());
        assertEquals("pending", response.getStatus());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void shouldFilterTasksForToday() {
        // Given
        TaskRequestDto todayTask = new TaskRequestDto();
        todayTask.setTitle("Завдання на сьогодні");
        todayTask.setDeadline(LocalDateTime.now()); // Сьогодні

        TaskRequestDto tomorrowTask = new TaskRequestDto();
        tomorrowTask.setTitle("Завдання на завтра");
        tomorrowTask.setDeadline(LocalDateTime.now().plusDays(1)); // Завтра

        taskService.createTask(todayTask);
        taskService.createTask(tomorrowTask);

        // When
        List<TaskResponseDto> todayTasks = taskService.getTasksForToday();

        // Then
        assertEquals(1, todayTasks.size());
        assertEquals("Завдання на сьогодні", todayTasks.get(0).getTitle());
    }
}