package com.coredesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime deadline;
    private String link;
    private LocalDateTime createdAt;
}