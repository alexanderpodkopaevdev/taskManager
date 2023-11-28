package ru.naumen.taskManager.services;

import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();

    void saveTask(Task task);

    void getTaskById(long id);

    List<Task> getTasksByBoardId(long id);

}
