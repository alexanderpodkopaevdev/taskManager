package ru.naumen.taskManager.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String nameBoard;
    @ManyToOne
    private User user;

    public Board(User user, String nameBoard) {
        this.nameBoard = nameBoard;
        this.user = user;
    }

    public Board(User user) {
        this.nameBoard = nameBoard;
        this.user = user;
    }
    public Board() {

    }


}