package Übung7.VerlorenImWald;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class RescueOperatorTest {
    @Test
    public void testCreateMap() {
        RescueNavigator navigator = new RescueNavigator(10);
        navigator.createMap("0 ,9 -> 5 ,9");
        assertEquals(ForestField.TREE, navigator.getMap()[9][0]);
        assertEquals(ForestField.TREE, navigator.getMap()[9][1]);
        assertEquals(ForestField.TREE, navigator.getMap()[9][2]);
        assertEquals(ForestField.TREE, navigator.getMap()[9][3]);
        assertEquals(ForestField.TREE, navigator.getMap()[9][4]);
        assertEquals(ForestField.TREE, navigator.getMap()[9][5]);
    }

    @Test
    public void testEstimatePath() {
        RescueNavigator navigator = new RescueNavigator(10);
        navigator.createMap("0 ,9 -> 5 ,9");
        navigator.estimatePath(0, 0);
        assertEquals(ForestField.PATH, navigator.getMap()[0][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[1][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[2][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[3][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[4][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[5][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[6][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[7][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[8][0]);
        assertEquals(ForestField.PATH, navigator.getMap()[9][0]);
    }
}
