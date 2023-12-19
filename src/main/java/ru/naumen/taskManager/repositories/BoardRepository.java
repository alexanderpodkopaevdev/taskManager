package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.models.User;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findByUser(User user);
}
