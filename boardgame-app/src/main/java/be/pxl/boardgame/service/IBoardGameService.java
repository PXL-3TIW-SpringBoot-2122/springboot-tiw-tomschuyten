package be.pxl.boardgame.service;

import be.pxl.boardgame.model.dto.BoardGameRequest;
import be.pxl.boardgame.model.dto.BoardGameResponse;

import java.util.List;

public interface IBoardGameService {
    void addBoardGame(BoardGameRequest boardGameRequest);
    List<BoardGameResponse> getAllBoardGames();
}
