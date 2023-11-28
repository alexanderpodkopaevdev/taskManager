package ru.naumen.taskManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.models.Task;
import ru.naumen.taskManager.services.BoardService;
import ru.naumen.taskManager.services.TaskService;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final TaskService taskService;


    @Autowired
    public BoardController(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @GetMapping("/allBoards")
    @ResponseBody
    public List<Board> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/addBoard/{boardName}")
    @ResponseBody
    public List<Board> addBoard(@PathVariable String boardName) {
        Board newBoard = new Board(boardName);
        boardService.saveBoard(newBoard);
        return getBoards();
    }

    @GetMapping("/addTask/{taskName}/{description}/{boardId}")
    @ResponseBody
    public List<Board> addTask(@PathVariable String taskName, @PathVariable String description, @PathVariable Long boardId) {
        Task newTask = new Task(taskName, description, boardService.getBoardById(boardId));
        taskService.saveTask(newTask);
        return getBoards();
    }

    @GetMapping("/allTasks")
    @ResponseBody
    public List<Task> getTasks() {
        return taskService.getAllTask();
    }
    @GetMapping("/getTaskByBoard/{boardId}")
    @ResponseBody
    public List<Task> getTaskByBoard(@PathVariable Long boardId) {
        return taskService.getTasksByBoardId(boardId);
    }

}
