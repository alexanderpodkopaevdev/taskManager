package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
