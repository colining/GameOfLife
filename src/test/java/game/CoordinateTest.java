package game;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CoordinateTest {
    @Test
    public void testGetSurroundings_notAtEdgeOrCorner() {
        Coordinate coordinate = new Coordinate(2, 2);
        Set<Coordinate> results = coordinate.getSurroundings(3);
        assertEquals(8, results.size());
        assertTrue(results.contains(new Coordinate(1, 1)));
        assertTrue(results.contains(new Coordinate(1, 2)));
        assertTrue(results.contains(new Coordinate(1, 3)));
        assertTrue(results.contains(new Coordinate(2, 1)));
        assertTrue(results.contains(new Coordinate(2, 3)));
        assertTrue(results.contains(new Coordinate(3, 1)));
        assertTrue(results.contains(new Coordinate(3, 2)));
        assertTrue(results.contains(new Coordinate(3, 3)));
    }

    @Test
    public void isValid() throws Exception {
        assertTrue(new Coordinate(1, 1).isValid(2));
        assertTrue(new Coordinate(1, 2).isValid(2));
        assertTrue(new Coordinate(2, 1).isValid(2));
        assertTrue(new Coordinate(2, 2).isValid(2));

        assertFalse(new Coordinate(0, 1).isValid(2));//越上界
        assertFalse(new Coordinate(3, 1).isValid(2));//越下界
        assertFalse(new Coordinate(1, 0).isValid(2));//越左界
        assertFalse(new Coordinate(1, 3).isValid(2));//越右界

        assertFalse(new Coordinate(0, 0).isValid(2)); //越左上角
        assertFalse(new Coordinate(0, 3).isValid(2)); //越右上角
        assertFalse(new Coordinate(3, 0).isValid(2)); //越左下角
        assertFalse(new Coordinate(3, 3).isValid(2)); //越右下角
    }

    @Test
    public void testGetSurroundings_atUpperEdge() {
        Coordinate coordinate = new Coordinate(1, 2);
        Set<Coordinate> results = coordinate.getSurroundings(3);
        assertEquals(5, results.size());
        assertTrue(results.contains(new Coordinate(1, 1)));
        assertTrue(results.contains(new Coordinate(1, 3)));
        assertTrue(results.contains(new Coordinate(2, 1)));
        assertTrue(results.contains(new Coordinate(2, 2)));
        assertTrue(results.contains(new Coordinate(2, 3)));

    }

    @Test
    public void testGetSurroundings_atUpperLeftCorner() {
        Coordinate coordinate = new Coordinate(1, 1);
        Set<Coordinate> results = coordinate.getSurroundings(3);
        assertEquals(3,results.size());
        assertTrue(results.contains(new Coordinate(1, 2)));
        assertTrue(results.contains(new Coordinate(2, 1)));
        assertTrue(results.contains(new Coordinate(2, 2)));
    }
    @Test
    public void testGetSurroundings_atBottomRightCorner() {
        Coordinate coordinate = new Coordinate(3, 3);
        Set<Coordinate> results = coordinate.getSurroundings(3);
        assertEquals(3,results.size());
        assertTrue(results.contains(new Coordinate(2, 2)));
        assertTrue(results.contains(new Coordinate(2, 3)));
        assertTrue(results.contains(new Coordinate(3, 2)));
    }
}