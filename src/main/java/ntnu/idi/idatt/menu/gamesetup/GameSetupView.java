package ntnu.idi.idatt.menu.gamesetup;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.AddPlayer;
import ntnu.idi.idatt.components.NewPlayer;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * The GameSetupView class represents the view for setting up a game.
 */
public class GameSetupView extends BorderPane {

  private final List<NewPlayer> playerList = new ArrayList<>();
  private final VBox playerBox = new VBox();
  private final VBox boardBox = new VBox();
  private Board selectedBoard;
  private TextButton selectedBoardButton;
  private final TextButton startButton;
  private TextButton loadPlayersButton;

  /**
   * Constructor for the GameSetupView class. It initializes the view with a layout for players and
   * boards.
   */
  public GameSetupView() {
    getStyleClass().add("game-setup");

    VBox left = new VBox();
    left.getStyleClass().add("game-setup-left");

    Label playersTitle = new Label("Players");
    playersTitle.getStyleClass().add("game-setup-title");

    playerBox.getStyleClass().add("game-setup-player-box");

    loadPlayersButton = new TextButton("Load Players");
    loadPlayersButton.getStyleClass().add("game-setup-load-players");

    left.getChildren().addAll(playersTitle, playerBox, loadPlayersButton);

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

  /**
   * Returns the list of players in the game setup.
   *
   * @return A list of Player objects.
   */
  public List<Player> getPlayers() {
    return playerList.stream().map(p -> new Player(p.getName(), new ArrayList<>())).toList();
  }

  public Board getSelectedBoard() {
    return selectedBoard;
  }

  /**
   * Sets the players in the game setup.
   *
   * @param min The minimum number of players.
   * @param max The maximum number of players.
   */
  public void setPlayers(int min, int max) {
    if (!ArgumentValidator.isValidInterval(min, max)) {
      throw new IllegalArgumentException("Invalid player interval");
    }
    playerList.clear();
    for (int i = 0; i < min; i++) {
      playerList.add(
          new NewPlayer(
              AssetRepository.SNL_COLORS.get(i),
              i + 1,
              "Player " + (i + 1),
              false
          )
      );
    }
    updatePlayerBox(min, max);
  }

  /**
   * Adds a new player to the game setup.
   *
   * @param playerName The name of the player.
   * @param min        The minimum number of players.
   * @param max        The maximum number of players.
   * @throws InvalidInputException if the player name is invalid or the interval is invalid.
   */
  public void addPlayer(String playerName, int min, int max) {
    if (!ArgumentValidator.isValidString(playerName)) {
      throw new InvalidInputException("Invalid player name");
    }
    if (!ArgumentValidator.isValidInterval(min, max)) {
      throw new InvalidInputException("Invalid player interval"); // this should never happen, but just in case
    }

    NewPlayer newPlayer = new NewPlayer(
        AssetRepository.SNL_COLORS.get(playerList.size()),
        playerList.size() + 1,
        playerName,
        true
    );
    newPlayer.setOnClick(() -> {
      for (NewPlayer p : playerList) {
        int playerNumber = p.getPlayerNumber();
        if (newPlayer.getPlayerNumber() < playerNumber) {
          p.setPlayerNumber(playerNumber - 1);
          p.setColor(AssetRepository.SNL_COLORS.get(playerNumber - 2));
        }
      }
      playerList.remove(newPlayer);
      updatePlayerBox(min, max);
    });
    playerList.add(newPlayer);
  }

  /**
   * Sets the boards in the game setup.
   *
   * @param boards The list of boards to be displayed.
   */
  public void setBoards(List<Board> boards) {
    if (!ArgumentValidator.isValidList(boards)) {
      throw new IllegalArgumentException("Invalid board list");
    }
    boardBox.getChildren().clear();
    for (Board board : boards) {
      TextButton boardButton = new TextButton(board.getTiles().size() + " tiles");
      boardButton.setOnAction(e -> {
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

  /**
   * Updates the player box with the current list of players.
   *
   * @param min The minimum number of players.
   * @param max The maximum number of players.
   * @throws InvalidInputException if the interval is invalid.
   */
  public void updatePlayerBox(int min, int max) {
    if (!ArgumentValidator.isValidInterval(min, max)) {
      throw new InvalidInputException("Invalid player interval");
    }
    playerBox.getChildren().clear();
    playerBox.getChildren().addAll(playerList);

    if (playerList.size() < max) {
      AddPlayer addPlayerComponent = new AddPlayer(); // new name for clarity
      addPlayerComponent.setOnClick(() -> {
        String newPlayerName = addPlayerComponent.getName();
        if (ArgumentValidator.isValidString(newPlayerName)) {
          this.addPlayer(newPlayerName, min, max);
          updatePlayerBox(min, max);
        } else {
          Router.showAlert("Invalid Player Name", "Please enter a valid player name.", "OK", null);
        }
      });

      playerBox.getChildren().add(addPlayerComponent);
    }
  }

  public void startButtonSetOnClick(Runnable runnable) {
    startButton.setOnAction(e -> runnable.run());
  }

  public void loadPlayersButtonSetOnClick(Runnable runnable) {
    loadPlayersButton.setOnAction(e -> runnable.run());
  }

  public void setLoadedPlayers(List<Player> players, int minPlayers, int maxPlayers) {
    playerList.clear();
    int playerCount = 0;
    for (Player p : players) {
      if (playerCount >= maxPlayers) {
        break;
      }

      Color playerColor = AssetRepository.SNL_COLORS.get(playerCount % AssetRepository.SNL_COLORS.size());
      NewPlayer newPlayer = new NewPlayer(
          playerColor,
          playerCount + 1,
          p.getName(),
          playerList.size() >= minPlayers
      );

      final int currentMin = minPlayers;
      final int currentMax = maxPlayers;

      newPlayer.setOnClick(() -> {
        if (playerList.size() > currentMin) {
          playerList.remove(newPlayer);
          for (int i = 0; i < playerList.size(); i++) {
            NewPlayer player = playerList.get(i);
            player.setPlayerNumber(i + 1);
            player.setColor(AssetRepository.SNL_COLORS.get(i % AssetRepository.SNL_COLORS.size()));
          }
          updatePlayerBox(currentMin, currentMax);
        } else {
          Router.showAlert("Error", "You must have at least " + currentMin + " players.", "OK", null);
        }
      });
      playerList.add(newPlayer);
      playerCount++;
    }
    while (playerList.size() < minPlayers) {
      Color playerColor = AssetRepository.SNL_COLORS.get(playerCount % AssetRepository.SNL_COLORS.size());
      NewPlayer newPlayer = new NewPlayer(
          playerColor,
          playerCount + 1,
          "Player " + (playerCount + 1),
          false
      );
      playerList.add(newPlayer);
    }
    updatePlayerBox(minPlayers, maxPlayers);
  }
}
