package ntnu.idi.idatt.UI.views;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.UI.components.AddPlayer;
import ntnu.idi.idatt.UI.components.NewPlayer;
import ntnu.idi.idatt.UI.components.TextButton;
import ntnu.idi.idatt.core.Board;
import ntnu.idi.idatt.core.GameConfig;
import ntnu.idi.idatt.core.Player;

public class GameSetupView extends VBox {

  List<NewPlayer> playerList;
  VBox playerBox;

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

    right.getChildren().add(boardTitle);

    HBox content = new HBox(left, right);
    content.getStyleClass().add("game-setup-content");

    TextButton startButton = new TextButton("Start");

    startButton.setOnAction(e -> {
      AppState.setCurrentGameConfig(
          new GameConfig(
              playerList.stream()
                  .map(newPlayer -> new Player(newPlayer.getName(), new ArrayList<>())).toList(),
              boards.getFirst(),
              0
          )
      );
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
