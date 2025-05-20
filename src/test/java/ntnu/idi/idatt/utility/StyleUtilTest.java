package ntnu.idi.idatt.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StyleUtilTest {

    @Mock
    private Scene mockScene;

    @Test
    void testApplyStyleIfExistsAndStylesheetExists() {
        String existingStylesheetPath = "/test-style.css"; // Make sure test-style.css is in src/test/resources!!

        ObservableList<String> stylesheets = FXCollections.observableArrayList();
        when(mockScene.getStylesheets()).thenReturn(stylesheets);

        StyleUtil.applyStyleIfExists(mockScene, existingStylesheetPath);

        URL resourceUrl = StyleUtil.class.getResource(existingStylesheetPath);
        assertNotNull(resourceUrl, "Test stylesheet '/test-style.css' must exist in src/test/resources for this test.");
        
        assertTrue(stylesheets.contains(resourceUrl.toExternalForm()), "Stylesheet should be added to the scene.");
    }

    @Test
    void testApplyStyleIfExistsAndStylesheetNotExist() {
        String nonExistentPath = "/nonexistent-stylesheet.css";
        
        
        ObservableList<String> stylesheets = FXCollections.observableArrayList();

        StyleUtil.applyStyleIfExists(mockScene, nonExistentPath);

        assertTrue(stylesheets.isEmpty(), "No stylesheet should be added if it doesn't exist.");
    }

    @Test
    void testToRgbStringWithOpaqueColor() {
        assertEquals("#FF0000", StyleUtil.toRgbString(Color.RED));
        assertEquals("#00FF00", StyleUtil.toRgbString(Color.LIME));
        assertEquals("#0000FF", StyleUtil.toRgbString(Color.BLUE));
        assertEquals("#000000", StyleUtil.toRgbString(Color.BLACK));
        assertEquals("#FFFFFF", StyleUtil.toRgbString(Color.WHITE));
        assertEquals("#FFFF00", StyleUtil.toRgbString(Color.YELLOW));
        assertEquals("#808080", StyleUtil.toRgbString(Color.GRAY));
    }

    @Test
    void testToRgbStringWithTransparentColor() {
        assertEquals("rgba(255, 0, 0, 0.500)", StyleUtil.toRgbString(new Color(1, 0, 0, 0.5)));
        assertEquals("rgba(0, 255, 0, 0.000)", StyleUtil.toRgbString(new Color(0, 1, 0, 0.0)));
        assertEquals("rgba(0, 0, 255, 0.753)", StyleUtil.toRgbString(new Color(0, 0, 1, 0.753)));
        assertEquals("rgba(255, 255, 255, 0.250)", StyleUtil.toRgbString(new Color(1, 1, 1, 0.25)));
        assertEquals("rgba(0, 0, 0, 0.100)", StyleUtil.toRgbString(new Color(0, 0, 0, 0.1)));
    }
}
