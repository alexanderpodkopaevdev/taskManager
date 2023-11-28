package ru.naumen.taskManager.services;

import ru.naumen.taskManager.models.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    private BoardRepository repository;

    public BoardServiceImpl(BoardRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Board> getAllBoards() {
        List<Board> result = new ArrayList<>();
        return repository.findAll();
    }

    @Override
    public void saveBoard(Board board) {
        repository.save(board);
    }

    @Override
    public void getBoardById(long id) {
        repository.findById(id);
    }
}
