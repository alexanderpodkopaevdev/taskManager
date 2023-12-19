package ru.naumen.taskManager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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
    @Getter
    private LocalDateTime date;
    private Boolean notificationSend;
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private State state;

    public Task(String taskName, String description, Board board, User user) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
        this.user = user;
        this.notificationSend = false;
        this.state = State.CREATED;
    }

    public Task(String taskName, String description, Board board, String date, User user) {
        this.taskName = taskName;
        this.description = description;
        this.board = board;
        this.date = LocalDateTime.parse(date);
        this.user = user;
        this.notificationSend = false;
        this.state = State.CREATED;

    }
    public Task() {
    }
}
