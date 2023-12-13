package ru.naumen.taskManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime date;
    @ManyToOne
    private User user;

    public Task(String taskName, String description, Board board, User user) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
        this.user = user;
    }

    public Task(String taskName, String description, Board board, String date, User user) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
        this.date = LocalDateTime.parse(date);
        this.user = user;

    }
    public Task() {

    }

    public LocalDateTime getDate() {
        return date;
    }
}
//контекст