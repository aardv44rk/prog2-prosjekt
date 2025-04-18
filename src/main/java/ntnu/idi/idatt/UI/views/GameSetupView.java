package ntnu.idi.idatt.UI.views;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.UI.components.NewPlayer;
import ntnu.idi.idatt.UI.components.TextButton;
import ntnu.idi.idatt.core.GameInfo;

public class GameSetupView extends VBox {

  List<NewPlayer> playerList;
  VBox playerBox;

  public GameSetupView() {

    getStyleClass().add("game-setup");

    GameInfo gameInfo = AppState.getSelectedGame();

    VBox left = new VBox();
    left.getStyleClass().add("game-setup-left");
    Label playersTitle = new Label("Players");
    playersTitle.getStyleClass().add("game-setup-title");

    playerList = new ArrayList<>();

    for (int i = 0; i < gameInfo.getPlayerMin(); i++) {
      playerList.add(
          new NewPlayer(AssetRepository.PLAYER_COLORS.get(i), "P" + (i + 1), "Player " + (i + 1)));
    }

    updatePlayerList();

    left.getChildren().addAll(playersTitle, playerBox);

    VBox right = new VBox();
    right.getStyleClass().add("game-setup-right");
    Label boardTitle = new Label("Board");
    boardTitle.getStyleClass().add("game-setup-title");

    right.getChildren().add(boardTitle);

    HBox content = new HBox(left, right);
    content.getStyleClass().add("game-setup-content");

    TextButton startButton = new TextButton("Start");

    getChildren().addAll(content, startButton);
  }

  public void updatePlayerList() {
    
  }
}
