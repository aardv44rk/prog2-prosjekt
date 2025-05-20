package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePlayerList extends VBox {

  List<HBox> playerList;
  List<Node> pieces;
  Node currentPiece;

  public GamePlayerList() {
    this.playerList = new ArrayList<>();
    this.pieces = new ArrayList<>();

    getStyleClass().add("game-player-list");
  }

  public void setPieces(LinkedHashMap<String, Node> players) {
    for (Entry<String, Node> entry : players.entrySet()) {
      HBox player = new HBox();
      player.getStyleClass().add("game-player-list-player");

      Node piece = entry.getValue();

      Label name = new Label(entry.getKey());
      name.getStyleClass().add("game-player-list-name");

      player.getChildren().addAll(piece, name);

      playerList.add(player);
      pieces.add(piece);
    }
    getChildren().addAll(playerList);
    setCurrentPiece(pieces.getFirst());
  }

  private void setCurrentPiece(Node piece) {
    if (currentPiece != null) {
      this.currentPiece.getStyleClass().remove("game-player-list-current-piece");
    }
    this.currentPiece = piece;
    this.currentPiece.getStyleClass().add("game-player-list-current-piece");
  }

  public void shuffle() {
    Node firstPiece = pieces.removeFirst();
    pieces.add(firstPiece);

    setCurrentPiece(pieces.getFirst());
  }

}
