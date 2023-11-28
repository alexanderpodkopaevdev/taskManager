package ru.naumen.taskManager.services;

import org.springframework.stereotype.Service;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.repositories.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardRepository repository;

    public BoardServiceImpl(BoardRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Board> getAllBoards() {
        List<Board> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
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
