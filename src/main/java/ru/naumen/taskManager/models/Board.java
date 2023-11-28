package ru.naumen.taskManager.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String nameBoard;
}
