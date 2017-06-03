package colining.cn;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CoordinateTest {
    @Test
    public void testGetSurroundings_notAtEdgeOrCorner(){
        Coordinate coordinate = new Coordinate(2,2);
        Set<Coordinate> results = coordinate.getSurroundings(3);
        assertEquals(8, results.size());
        assertTrue(results.contains(new Coordinate(1,1)));
        assertTrue(results.contains(new Coordinate(1,2)));
        assertTrue(results.contains(new Coordinate(1,3)));
        assertTrue(results.contains(new Coordinate(2,1)));
        assertTrue(results.contains(new Coordinate(2,3)));
        assertTrue(results.contains(new Coordinate(3,1)));
        assertTrue(results.contains(new Coordinate(3,2)));
        assertTrue(results.contains(new Coordinate(3,3)));
    }
}