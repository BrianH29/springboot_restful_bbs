package com.bh.restful.service;

import com.bh.restful.domain.Board;
import com.bh.restful.dto.BoardDto;
import com.bh.restful.repsitory.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public List<Board> getBoarList() {
        return boardRepository.findAll();
    }

    @Transactional
    public Long addBoard(Board board) {
        return boardRepository.save(board).getId();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<Board> findPost = boardRepository.findById(id);

        //error check
        findPost.orElseThrow(() -> new IllegalStateException(
                "board" + id + "does not exists"
        ));

        Board board = findPost.get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .content(board.getContent())
                .build();

        return boardDto;
    }

    @Transactional
    public void updateBoard(Long id, String title, String content) {
        Optional<Board> find = boardRepository.findById(id);
        Board board = find.get();

        if(title != null && title.length() > 0 && !Objects.equals(board.getTitle(), title)){
            BoardDto boardDto = BoardDto.builder()
                    .title(title)
                    .build();

            boardRepository.save(boardDto.toEntity());
        }

        if(content != null && content.length() > 0 && !Objects.equals(board.getContent(), content)){
            System.out.println(content);
            BoardDto boardDto = BoardDto.builder()
                    .content(content)
                    .build();

            boardRepository.save(boardDto.toEntity());
        }
    }
}
