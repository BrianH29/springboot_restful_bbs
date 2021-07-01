package com.bh.restful.controller;

import com.bh.restful.domain.Board;
import com.bh.restful.dto.BoardDto;
import com.bh.restful.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public List<Board> getBoardList(){
        return boardService.getBoarList();
    }

    @PostMapping("/write")
    public void addBoard(@RequestBody Board board){
        boardService.addBoard(board);
    }

    @GetMapping("/list/detail/{no}")
    public BoardDto detail(@PathVariable("no") Long id){
        return boardService.getPost(id);
    }

    @GetMapping("/list/edit/{no}")
    public BoardDto showUpdate(@PathVariable("no") Long id){
        return boardService.getPost(id);
    }

    @PutMapping("/list/edit/{no}")
    public void update(
            @PathVariable("no") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        boardService.updateBoard(id, title, content);
    }
}
