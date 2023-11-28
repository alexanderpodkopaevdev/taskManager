package ru.naumen.taskManager.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String nameBoard;

    public Board(String nameBoard) {
        this.nameBoard = nameBoard;
    }

    public Board() {

    }
}