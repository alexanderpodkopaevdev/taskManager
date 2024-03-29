package ru.naumen.taskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.services.BoardService;
import ru.naumen.taskManager.services.TaskService;
import ru.naumen.taskManager.services.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class TgBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.name}")
    private String botName;

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("new message");
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            switch (message) {
                case "my task" -> getAllTasks(chatId);
                case "my board" -> getAllBoards(chatId);
                case "today" -> getTodayTask(chatId);
                default -> sendNotification(chatId, message);
            }
        }
    }

    private void getAllTasks(String chatId) {
        List<Task> tasks = taskService.getTasksByUser(userService.getUserByTgId(chatId));
        for (Task task : tasks) {
            String message =  "Доска: " + task.getBoard().getNameBoard() + "\nТема: " + task.getTaskName() + "\nОписание: " + task.getDescription() +
                    "\nСтатус: " + task.getState() +
                    "\nДата выполнения: " + task.getDate();
            sendNotification(chatId, message);
        }
    }

    private void getTodayTask(String chatId) {
        List<Task> tasks = taskService.getTaskByDate(LocalDate.ofInstant(
                new Date().toInstant(), ZoneId.systemDefault()), userService.getUserByTgId(chatId));
        for (Task task : tasks) {
            String message =  "Доска: " + task.getBoard().getNameBoard() + "\nТема: " + task.getTaskName() + "\nОписание: " + task.getDescription() +
                    "\nСтатус: " + task.getState() +
                    "\nДата выполнения: " + task.getDate();
            sendNotification(chatId, message);
        }
    }

    private void getAllBoards(String chatId) {
        List<Board> boards = boardService.getBoardsByUser(userService.getUserByTgId(chatId));
        for (Board board : boards) {
            String message =  board.getId() + "\nНазвание: " + board.getNameBoard();
            sendNotification(chatId, message);
        }
    }

    public void sendNotification(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        System.out.println(sendMessage.toString());
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок отправки сообщения
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


}
