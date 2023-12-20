package ru.naumen.taskManager.services;

import org.springframework.stereotype.Service;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.User;
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
    public Board getBoardById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Board> getBoardsByUser(User user) {
        if (user.getRoles().stream().anyMatch(i -> i.getName().equals("admin"))) {
            return getAllBoards();
        } else {
            return repository.findByUser(user);
        }
    }

    @Override
    public void deleteBoardById(long id) {
        repository.deleteById(id);
    }
}
