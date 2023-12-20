package ru.naumen.taskManager.services;

import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.User;

import java.util.List;

public interface BoardService {

    List<Board> getAllBoards();

    void saveBoard(Board board);

    Board getBoardById(long id);

    List<Board> getBoardsByUser(User user);

    void deleteBoardById(long id);
}
