package com.coredesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskRequestDto {

    @NotBlank(message = "Назва завдання не може бути порожньою")
    private String title;

    private String description;

    @NotNull(message = "Дата дедлайну обов'язкова")
    @FutureOrPresent(message = "Дата дедлайну не може бути в минулому")
    private LocalDateTime deadline;

    private String link;
}