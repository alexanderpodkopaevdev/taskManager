package ru.naumen.taskManager.services;

import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.State;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    List<Task> getAllTask();

    List<Task> getAllTaskForNotification();

    void saveTask(Task task);

    Task getTaskById(long id);

    List<Task> getTasksByBoardId(long id);

    List<Task> getTasksByUser(User user);

    List<Task> getTaskByDate(LocalDate date, User user);

    void updateState(Task task, State state);

    void editTask(Task task);

    void deleteTask(Long taskId);

    void editState(Long taskId);

}
