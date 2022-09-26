package be.pxl.boardgame.controller;

import be.pxl.boardgame.model.BoardGame;
import be.pxl.boardgame.model.dto.BoardGameRequest;
import be.pxl.boardgame.model.dto.BoardGameResponse;
import be.pxl.boardgame.service.IBoardGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgame")
@RequiredArgsConstructor
@Slf4j
public class BoardGameController {

    private final IBoardGameService boardGameService;

    @GetMapping
    public ResponseEntity<List<BoardGameResponse>> getAllBoardGames() {
        return new ResponseEntity(boardGameService.getAllBoardGames(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getBoardGame(@PathVariable Long id) {
        log.info("Get boardgame with id {}", id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBoardGame(@RequestBody BoardGameRequest boardGameRequest) {
        boardGameService.addBoardGame(boardGameRequest);
    }
}
