package ru.naumen.taskManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String taskName;
    private String description;
    @ManyToOne
    private Board board;
    private LocalDate date;

    public Task(String taskName, String description, Board board) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
    }

    public Task(String taskName, String description, Board board, String date) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
        this.date = LocalDate.parse(date);

    }
    public Task() {

    }
}
//контекст