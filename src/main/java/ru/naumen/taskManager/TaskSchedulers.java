package ru.naumen.taskManager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.services.TaskService;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskSchedulers {

    private TaskService taskService;

    public TaskSchedulers(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(fixedRate = 30000) // Запуск каждые 30 секунд
    public void checkTaskTimes() {
        List<Task> tasks = taskService.getAllTask();
        for (Task task : tasks) {
            if (shouldExecuteTask(task.getDate())) {
                executeTaskAction(task);
            }
        }
        System.out.println("Проверка времени в задачах и выполнение действия...");
    }

    private void executeTaskAction(Task task) {
        System.out.println("Выполнение действия с задачей: " + task.getId());
    }

    private boolean shouldExecuteTask(LocalDateTime taskTime) {
        //TODO сскорректировать проверку времени
        if (taskTime != null) {

            LocalDateTime currentTime = LocalDateTime.now();
            return currentTime.isAfter(taskTime);
        } else {
            return true;
        }
    }
}