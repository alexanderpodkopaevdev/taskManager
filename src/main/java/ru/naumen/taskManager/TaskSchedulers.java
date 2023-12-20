package ru.naumen.taskManager;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.services.TaskService;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskSchedulers {

    private TaskService taskService;
    private TgBot tgBot;

    public TaskSchedulers(TaskService taskService, TgBot tgBot) {
        this.taskService = taskService;
        this.tgBot = tgBot;
    }

    @Scheduled(fixedRate = 30000) // Запуск каждые 30 секунд
    public void checkTaskTimes() {
        List<Task> tasks = taskService.getAllTaskForNotification();
        for (Task task : tasks) {
            System.out.println(task.getNotificationSend());
            if (task.getNotificationSend() != null && !task.getNotificationSend()) {
                if (shouldExecuteTask(task.getDate())) {
                    executeTaskAction(task);
                }
            }
        }
        System.out.println("Проверка времени в задачах и выполнение действия...");
    }

    private void executeTaskAction(Task task) {
        String chatId = task.getUser().getTgID();
        if (chatId != null) {
            System.out.println("chat id " + chatId);
            String message =  "Пришло время задачи " + task.getTaskName() + "\nОписание: " + task.getDescription();
            tgBot.sendNotification(chatId, message);
            task.setNotificationSend(true);
            taskService.saveTask(task);
        }
    }

    private boolean shouldExecuteTask(LocalDateTime taskTime) {
        if (taskTime != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            return currentTime.isAfter(taskTime);
        } else {
            return true;
        }
    }
}