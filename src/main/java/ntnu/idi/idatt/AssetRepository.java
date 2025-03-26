package ntnu.idi.idatt;

import java.util.List;
import java.util.Objects;

public class AssetRepository {

  public static List<String> getGlobalStyles() {
    return List.of(
        getResource("styles/global/global.css"),
        getResource("styles/global/colors.css"),
        getResource("styles/global/components.css"),
        getResource("styles/global/fonts.css")
    );
  }

  private static String getResource(String path) {
    return Objects.requireNonNull(
        AssetRepository.class.getResource("/" + path)
    ).toExternalForm();
  }
}
