package ru.naumen.taskManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.models.User;
import ru.naumen.taskManager.services.BoardService;
import ru.naumen.taskManager.services.TaskService;
import ru.naumen.taskManager.services.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final TaskService taskService;
    private final UserService userService;


    @Autowired
    public BoardController(BoardService boardService, TaskService taskService, UserService userService) {
        this.boardService = boardService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/allBoards")
    @ResponseBody
    public List<Board> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/dashboard")
    public String getAllBoards(Model model) {
        model.addAttribute("boards", boardService.getBoardsByUser(getCurrentUser()));
        return "dashboard";
    }

    @GetMapping("/addDashboard")
    public String addBoard(Model model){
        model.addAttribute("boardForm", new Board());
        return "addDashboard";
    }

    @PostMapping("/addDashboard")
    public String addBoard(@ModelAttribute("boardForm") Board boardForm){
        boardForm.setUser(getCurrentUser());
        boardService.saveBoard(boardForm);
        return "redirect:/dashboard";
    }

    @GetMapping("/addBoard/{boardName}")
    @ResponseBody
    public List<Board> addBoard(@PathVariable String boardName) {
        Board newBoard = new Board(getCurrentUser(), boardName);
        boardService.saveBoard(newBoard);
        return getBoards();
    }

    @GetMapping("/addTask/{taskName}/{description}/{boardId}")
    @ResponseBody
    public List<Board> addTask(@PathVariable String taskName, @PathVariable String description, @PathVariable Long boardId) {
        Task newTask = new Task(taskName, description, boardService.getBoardById(boardId), getCurrentUser());
        taskService.saveTask(newTask);
        return getBoards();
    }

    @GetMapping("/addTaskWithDate/{taskName}/{description}/{boardId}/{date}")
    @ResponseBody
    public List<Task> addTaskWithDate(@PathVariable String taskName, @PathVariable String description, @PathVariable Long boardId, @PathVariable String date) {
        Task newTask = new Task(taskName, description, boardService.getBoardById(boardId), date, getCurrentUser());
        taskService.saveTask(newTask);
        return getTasks();
    }


    @GetMapping("/allTasks")
    @ResponseBody
    public List<Task> getTasks() {
        return taskService.getTasksByUser(getCurrentUser());
        //return taskService.getAllTask();
    }

    @GetMapping("/getTaskByBoard/{boardId}")
    @ResponseBody
    public List<Task> getTaskByBoard(@PathVariable Long boardId) {
        return taskService.getTasksByBoardId(boardId);
    }

    @GetMapping("/getTasksByBoard/{boardId}")
    public String getTaskByBoard(@PathVariable Long boardId, Model model) {
        model.addAttribute("board", boardId);
        model.addAttribute("tasks", taskService.getTasksByBoardId(boardId));
        return "task_dashboard";
    }

    @GetMapping("/getTaskToday")
    @ResponseBody
    public List<Task> getTaskToday() {
        return taskService.getTaskByDate(LocalDate.ofInstant(
                new Date().toInstant(), ZoneId.systemDefault()));
    }


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByName(currentPrincipalName);
    }

    @GetMapping("/deleteTask/{boardId}/{taskId}")
    public String getTaskByBoard(@PathVariable Long boardId, @PathVariable Long taskId, Model model) {
        taskService.deleteTask(taskId);
        model.addAttribute("tasks", taskService.getTasksByBoardId(boardId));
        return "task_dashboard";
    }

    @GetMapping("/addTask/{boardId}")
    public String addTask(@PathVariable Long boardId, Model model){
        model.addAttribute("taskForm", new Task(boardService.getBoardById(boardId)));
        return "/addTask";
    }

    @PostMapping("addTask")
    public String addTask(@ModelAttribute("taskForm") Task taskForm){
        taskForm.setUser(getCurrentUser());
        taskService.saveTask(taskForm);
        return "redirect:/dashboard";
    }

}
