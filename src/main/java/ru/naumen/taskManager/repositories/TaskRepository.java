package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.models.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByBoard(Board board);

    List<Task> findByDate(LocalDate date);

    List<Task> findByUser(User user);

    List<Task> findByNotificationSend(Boolean notificationSend);


}
