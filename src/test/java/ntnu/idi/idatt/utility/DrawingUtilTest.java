package ntnu.idi.idatt.utility;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrawingUtilTest {

    private Pane parent;
    private Rectangle node;
    private Rectangle offsetNode;

    @BeforeEach
    void setUp() {
        parent = new Pane();
        node = new Rectangle(50, 50);
        offsetNode = new Rectangle(20, 20);

        parent.getChildren().addAll(node, offsetNode);

        // Set positions for testing
        node.setLayoutX(100);
        node.setLayoutY(100);
        offsetNode.setLayoutX(0);
        offsetNode.setLayoutY(0);
    }

    @Test
    void testGetCenterCoords() {
        Point2D center = DrawingUtil.getCenterCoords(parent, node);
        assertEquals(125, center.getX(), 0.01); // Center X = 100 + 50/2
        assertEquals(125, center.getY(), 0.01); // Center Y = 100 + 50/2
    }

    @Test
    void testGetCenterCoordsOffsetForNode() {
        Point2D centerWithOffset = DrawingUtil.getCenterCoordsOffsetForNode(parent, node, offsetNode);
        assertEquals(115, centerWithOffset.getX(), 0.01); // Center X adjusted for offsetNode width
        assertEquals(115, centerWithOffset.getY(), 0.01); // Center Y adjusted for offsetNode height
    }
}