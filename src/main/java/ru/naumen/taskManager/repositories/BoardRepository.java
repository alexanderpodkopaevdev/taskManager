package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
