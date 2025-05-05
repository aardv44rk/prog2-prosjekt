package ntnu.idi.idatt.menu.gameSetup;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.UI.components.AddPlayer;
import ntnu.idi.idatt.UI.components.NewPlayer;
import ntnu.idi.idatt.UI.components.TextButton;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Player;

public class GameSetupView extends BorderPane {

  private final List<NewPlayer> playerList = new ArrayList<>();
  private final VBox playerBox = new VBox();
  private final VBox boardBox = new VBox();
  private Board selectedBoard;
  private TextButton selectedBoardButton;
  private final TextButton startButton;

  public GameSetupView() {
    getStyleClass().add("game-setup");

    VBox left = new VBox();
    left.getStyleClass().add("game-setup-left");

    Label playersTitle = new Label("Players");
    playersTitle.getStyleClass().add("game-setup-title");

    playerBox.getStyleClass().add("game-setup-player-box");

    left.getChildren().addAll(playersTitle, playerBox);

    VBox right = new VBox();
    right.getStyleClass().add("game-setup-right");

    Label boardTitle = new Label("Board");
    boardTitle.getStyleClass().add("game-setup-title");

    boardBox.getStyleClass().add("game-setup-board-box");

    right.getChildren().addAll(boardTitle, boardBox);

    HBox startContainer = new HBox();
    startContainer.getStyleClass().add("game-setup-start");

    startButton = new TextButton("Start");
    startContainer.getChildren().add(startButton);

    setLeft(left);
    setRight(right);
    setBottom(startContainer);

  }

  public List<Player> getPlayers() {
    return playerList.stream().map(p -> new Player(p.getName(), new ArrayList<>())).toList();
  }

  public Board getSelectedBoard() {
    return selectedBoard;
  }

  public void setPlayers(int min, int max) {
    playerList.clear();
    for (int i = 0; i < max - min; i++) {
      playerList.add(
          new NewPlayer(
              AssetRepository.PLAYER_COLORS.get(i),
              i + 1,
              "Player " + (i + 1),
              false
          )
      );
    }
    updatePlayerBox(min, max);
  }

  public void addPlayer(String playerName, int min, int max) {
    NewPlayer newPlayer = new NewPlayer(
        AssetRepository.PLAYER_COLORS.get(playerList.size()),
        playerList.size() + 1,
        playerName,
        true
    );
    newPlayer.setOnClick(() -> {
      for (NewPlayer p : playerList) {
        int playerNumber = p.getPlayerNumber();
        if (newPlayer.getPlayerNumber() < playerNumber) {
          p.setPlayerNumber(playerNumber - 1);
          p.setColor(AssetRepository.PLAYER_COLORS.get(playerNumber - 2));
        }
      }
      playerList.remove(newPlayer);
      updatePlayerBox(min, max);
    });
    playerList.add(newPlayer);
  }

  public void setBoards(List<Board> boards) {
    boardBox.getChildren().clear();
    for (Board board : boards) {
      TextButton boardButton = new TextButton(board.getTiles().size() + " tiles");
      boardButton.setOnMouseClicked(e -> {
        if (selectedBoardButton != null) {
          selectedBoardButton.getStyleClass().remove("game-setup-selected-board");
        }
        selectedBoardButton = boardButton;
        selectedBoard = board;
        selectedBoardButton.getStyleClass().add("game-setup-selected-board");
      });
      if (selectedBoardButton == null) {
        selectedBoardButton = boardButton;
        selectedBoard = board;
        selectedBoardButton.getStyleClass().add("game-setup-selected-board");
      }
      boardBox.getChildren().add(boardButton);
    }
  }

  public void updatePlayerBox(int min, int max) {
    playerBox.getChildren().clear();
    playerBox.getChildren().addAll(playerList);

    if (playerList.size() < max) {
      AddPlayer addPlayer = new AddPlayer();
      addPlayer.setOnClick(() -> {
        addPlayer(addPlayer.getName(), min, max);
        updatePlayerBox(min, max);
      });

      playerBox.getChildren().add(addPlayer);
    }
  }

  public void startButtonSetOnClick(Runnable runnable) {
    startButton.setOnMouseClicked(e -> runnable.run());
  }
}
