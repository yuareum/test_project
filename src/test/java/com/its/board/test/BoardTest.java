package com.its.board.test;

import com.its.board.BoardDTO;
import com.its.board.BoardEntity;
import com.its.board.BoardRepository;
import com.its.board.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;

    public BoardDTO newBoard(){
        BoardDTO board = new BoardDTO("testTitle","testWriter","testContents");
        return board;
    }
    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("게시글 작성 test")
    public void boardSaveTest() {
        BoardDTO boardDTO = newBoard();
        Long saveId = boardService.save(boardDTO);

        BoardDTO board = boardService.findById(saveId);
        String boardWriter = board.getBoardWriter();

        assertThat(boardDTO.getBoardWriter()).isEqualTo(boardWriter);
    }
}
