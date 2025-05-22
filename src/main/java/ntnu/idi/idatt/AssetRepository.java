package ntnu.idi.idatt;

import java.util.List;
import javafx.scene.paint.Color;

/**
 * Asset repository for storing color constants used in the game.
 */
public class AssetRepository {

  public static Color PLAYER_RED = Color.web("E57373");
  public static Color PLAYER_YELLOW = Color.web("FFD54F");
  public static Color PLAYER_GREEN = Color.web("AED581");
  public static Color PLAYER_BLUE = Color.web("64B5F6");
  public static Color PLAYER_PURPLE = Color.web("BA68C8");

  public static List<Color> SNL_COLORS = List.of(
      PLAYER_RED, PLAYER_YELLOW, PLAYER_GREEN, PLAYER_BLUE, PLAYER_PURPLE
  );

  public static Color LADDER_UP = Color.web("81C784");
  public static Color LADDER_DOWN = Color.web("FF8A65");

  public static Color LADDER_START_UP = Color.web("A5D6A7");
  public static Color LADDER_END_UP = Color.web("C8E6C9");

  public static Color LADDER_START_DOWN = Color.web("FFAB91");
  public static Color LADDER_END_DOWN = Color.web("FFE0B2");

  public static List<Color> TAR_COLORS = List.of(
      PLAYER_RED, PLAYER_YELLOW, PLAYER_GREEN, PLAYER_BLUE, PLAYER_PURPLE
  );

  public static Color TAR_START = Color.web("77FF55");

  public static List<Color> LUDO_COLORS = List.of(
      PLAYER_RED, PLAYER_YELLOW, PLAYER_GREEN, PLAYER_BLUE
  );

  public static Color LUDO_GOAL = Color.web("FCFCFA");
}
