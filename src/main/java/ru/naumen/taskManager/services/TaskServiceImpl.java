package ru.naumen.taskManager.services;

import org.springframework.stereotype.Service;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.State;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.models.User;
import ru.naumen.taskManager.repositories.BoardRepository;
import ru.naumen.taskManager.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;


    public TaskServiceImpl(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }
    @Override
    public List<Task> getAllTask() {
        List<Task> result = new ArrayList<>();
        taskRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getTasksByBoardId(long id) {
        return taskRepository.findByBoard(boardRepository.findById(id).get());
    }

    @Override
    public List<Task> getTasksByUser(User user) {
        if (user.getRoles().stream().anyMatch(i -> i.getName().equals("admin"))) {
            return getAllTask();
        } else {
            return taskRepository.findByUser(user);
        }
    }

    @Override
    public List<Task> getTaskByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }

    @Override
    public void updateState(Task task, State state) {
        task.setState(state);
        saveTask(task);
    }

    @Override
    public void editTask(Task task) {
        //TODO edit
        saveTask(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
