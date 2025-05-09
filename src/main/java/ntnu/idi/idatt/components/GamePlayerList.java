package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class GamePlayerList extends VBox {

  List<HBox> playerList;
  List<UISnakesAndLaddersPiece> pieces;
  UISnakesAndLaddersPiece currentPiece;

  public GamePlayerList() {
    this.playerList = new ArrayList<>();
    this.pieces = new ArrayList<>();

    getStyleClass().add("game-player-list");
  }

  public void setPieces(List<Pair<String, UISnakesAndLaddersPiece>> players) {
    for (Pair<String, UISnakesAndLaddersPiece> pair : players) {
      HBox player = new HBox();
      player.getStyleClass().add("game-player-list-player");

      UISnakesAndLaddersPiece piece = new UISnakesAndLaddersPiece(pair.getValue().getColor());

      Label name = new Label(pair.getKey());
      name.getStyleClass().add("game-player-list-name");

      player.getChildren().addAll(piece, name);

      playerList.add(player);
      pieces.add(piece);
    }
    getChildren().addAll(playerList);
    setCurrentPiece(pieces.getFirst());
  }

  public void setCurrentPiece(UISnakesAndLaddersPiece piece) {
    if (currentPiece != null) {
      this.currentPiece.getStyleClass().remove("game-player-list-current-piece");
    }
    this.currentPiece = piece;
    this.currentPiece.getStyleClass().add("game-player-list-current-piece");
  }

  public void shuffle() {
    UISnakesAndLaddersPiece firstPiece = pieces.removeFirst();
    pieces.add(firstPiece);

    setCurrentPiece(pieces.getFirst());
  }

}
