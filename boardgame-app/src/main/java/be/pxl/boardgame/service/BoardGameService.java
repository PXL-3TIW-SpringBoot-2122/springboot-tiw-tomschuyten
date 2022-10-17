package be.pxl.boardgame.service;

import be.pxl.boardgame.model.BoardGame;
import be.pxl.boardgame.model.dto.BoardGameRequest;
import be.pxl.boardgame.model.dto.BoardGameResponse;
import be.pxl.boardgame.repository.BoardGameRepository;
import be.pxl.boardgame.service.contract.IBoardGameService;
import be.pxl.boardgame.service.contract.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardGameService implements IBoardGameService {

    private final BoardGameRepository boardGameRepository;

    @Override
    public void addBoardGame(BoardGameRequest boardGameRequest) {

        BoardGame boardGame = BoardGame.builder()
                .name(boardGameRequest.getName())
                .build();

        boardGameRepository.save(boardGame);
    }

    @Override
    @LogExecutionTime
    public List<BoardGameResponse> getAllBoardGames() {

        List<BoardGame> boardGameList = boardGameRepository.findAll();
        return boardGameList.stream().map(this::mapToBoardGameResponse).collect(Collectors.toList());
    }

    private BoardGameResponse mapToBoardGameResponse(BoardGame boardGame) {
        return BoardGameResponse.builder()
                .name(boardGame.getName())
                .build();
    }
}
