package com.bh.restful.service;

import com.bh.restful.domain.Board;
import com.bh.restful.dto.BoardDto;
import com.bh.restful.repsitory.BoardRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    String writer = "JJ";
    String title = "Helllo";
    String content = "Hello to Java World";

    @Test
    void getBoarList() {
        //given
        BoardDto build = BoardDto.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        //when
        boardRepository.save(build.toEntity());
        List<Board> result = boardRepository.findAll();
        //then
        assertThat(result.get(0).getWriter()).isEqualTo(writer);
    }

    @Test
    void addBoard() {
        //given
        BoardDto boardDto = BoardDto.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        //when
        Board board = boardRepository.save(boardDto.toEntity());
        List<Board> result = boardRepository.findAll();
        //then
        assertThat(result.get(0).getTitle()).isEqualTo(board.getTitle());
    }

    @Test
    void getPost() {
        //given
        BoardDto boardDto = BoardDto.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        //when
        Long id = boardRepository.save(boardDto.toEntity()).getId();
        Optional<Board> result = boardRepository.findById(id);

        //then
        assertThat(result.get().getWriter()).isEqualTo(writer);
    }

    @Test
    void updateBoard() {
        //given
        BoardDto boardDto = BoardDto.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        Long id = boardRepository.save(boardDto.toEntity()).getId();
        Optional<Board> board = boardRepository.findById(id);

        //when
        String title2 = "edit text";

        BoardDto bb = BoardDto.builder()
                .title(title2)
                .build();

        //then
        assertThat(boardDto.getTitle()).isNotEqualTo(bb.getTitle());
    }
}