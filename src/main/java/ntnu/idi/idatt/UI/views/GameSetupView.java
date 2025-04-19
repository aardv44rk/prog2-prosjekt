package ntnu.idi.idatt.UI.views;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.Router;
import ntnu.idi.idatt.UI.components.AddPlayer;
import ntnu.idi.idatt.UI.components.NewPlayer;
import ntnu.idi.idatt.UI.components.TextButton;
import ntnu.idi.idatt.core.Board;
import ntnu.idi.idatt.core.GameConfig;
import ntnu.idi.idatt.core.Player;

public class GameSetupView extends VBox {

  private final List<NewPlayer> playerList;
  private final VBox playerBox;
  private Board selectedBoard;
  private TextButton selectedBoardButton;

  public GameSetupView() {

    if (AppState.getSelectedGame() == null) {
      throw new IllegalStateException("No selected game");
    }

    getStyleClass().add("game-setup");

    VBox left = new VBox();
    left.getStyleClass().add("game-setup-left");
    Label playersTitle = new Label("Players");
    playersTitle.getStyleClass().add("game-setup-title");

    playerList = new ArrayList<>();
    playerBox = new VBox();

    for (int i = 0; i < AppState.getSelectedGame().getPlayerMin(); i++) {
      NewPlayer newPlayer = new NewPlayer(AssetRepository.PLAYER_COLORS.get(i), "P" + (i + 1) + ":",
          "Player " + (i + 1));
      newPlayer.setOnClick(() -> {
        playerList.remove(newPlayer);
        updatePlayerBox();
      });
      playerList.add(newPlayer);
    }

    updatePlayerBox();

    left.getChildren().addAll(playersTitle, playerBox);

    VBox right = new VBox();
    right.getStyleClass().add("game-setup-right");
    Label boardTitle = new Label("Board");
    boardTitle.getStyleClass().add("game-setup-title");

    List<Board> boards = AppState.getSelectedGame().getBoardOptions();

    List<TextButton> boardList = new ArrayList<>();
    VBox boardBox = new VBox();

    for (int i = 0; i < boards.size(); i++) {
      Board board = boards.get(i);
      TextButton boardButton = new TextButton((i + 1) + ": " + board.getTiles().size());

      boardButton.setOnMouseClicked(e -> {
        if (selectedBoardButton != null) {
          selectedBoardButton.getStyleClass().remove("game-setup-selected-board");
        }
        selectedBoardButton = boardButton;
        selectedBoardButton.getStyleClass().add("game-setup-selected-board");
        selectedBoard = board;
      });

      boardList.add(boardButton);
      boardBox.getChildren().add(boardButton);
    }

    selectedBoardButton = boardList.getFirst();
    selectedBoardButton.getStyleClass().add("game-setup-selected-board");

    right.getChildren().addAll(boardTitle, boardBox);

    HBox content = new HBox(left, right);
    content.getStyleClass().add("game-setup-content");

    TextButton startButton = new TextButton("Start");

    startButton.setOnAction(e -> {
      AppState.setCurrentGameConfig(
          new GameConfig(
              playerList.stream()
                  .map(newPlayer -> new Player(newPlayer.getName(), new ArrayList<>())).toList(),
              selectedBoard,
              0
          )
      );
      Router.navigateTo("game");
    });

    getChildren().addAll(content, startButton);
  }

  public void updatePlayerBox() {
    playerBox.getChildren().clear();
    playerBox.getChildren().addAll(playerList);

    if (playerList.size() < AppState.getSelectedGame().getPlayerMax()) {
      AddPlayer addPlayer = new AddPlayer();
      addPlayer.setOnClick(() -> {
        addPlayerToList(addPlayer.getName());
        updatePlayerBox();
      });

      playerBox.getChildren().add(addPlayer);
    }
  }

  public void addPlayerToList(String name) {
    if (playerList.size() >= AppState.getSelectedGame().getPlayerMax()) {
      throw new IllegalStateException("Already max players");
    }

    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Player name cannot be null or empty");
    }

    playerList.add(
        new NewPlayer(AssetRepository.PLAYER_COLORS.get(playerList.size()),
            "P" + (playerList.size() + 1) + ":", name)
    );
  }
}
