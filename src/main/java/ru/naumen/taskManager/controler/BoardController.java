package ru.naumen.taskManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.taskManager.models.Board;
import ru.naumen.taskManager.services.BoardService;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
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
}
