package ru.naumen.taskManager.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String taskName;
    private String description;

}
