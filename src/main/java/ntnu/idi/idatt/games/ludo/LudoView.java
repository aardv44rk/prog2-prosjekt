package ntnu.idi.idatt.games.ludo;

import java.util.LinkedHashMap;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GamePlayerList;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.components.UIDie;
import ntnu.idi.idatt.components.UILudoPiece;

public class LudoView extends BorderPane {

  private final GamePlayerList playerList;
  private final UIDie die;
  private final TextButton rollButton;

  public LudoView() {
    getStyleClass().add("ludo-view");

    BorderPane left = new BorderPane();
    left.getStyleClass().add("ludo-die");

    playerList = new GamePlayerList();

    this.die = new UIDie(6);

    rollButton = new TextButton("Roll");

    VBox dieBox = new VBox();
    dieBox.getStyleClass().add("ludo-die-box");
    dieBox.getChildren().addAll(die, rollButton);

    left.setTop(playerList);
    left.setBottom(dieBox);

    setLeft(left);
  }

  public void setPlayerList(LinkedHashMap<String, Node> players) {
    playerList.setPieces(players);
  }

  public void shufflePlayerList() {
    playerList.shuffle();
  }

  public void setDieEyes(int eyes) {
    System.out.println(eyes);
  }

  public void setBoard(Node board) {
    BorderPane borderPane = new BorderPane(board);
    borderPane.getStyleClass().add("ludo-board-container");
    setCenter(borderPane);
  }

  public void highlightPieces(List<UILudoPiece> pieces) {

  }

  public void resetPieces() {

  }

  public void rollSetOnClick(Runnable runnable) {
    rollButton.setOnAction(e -> runnable.run());
  }
}
