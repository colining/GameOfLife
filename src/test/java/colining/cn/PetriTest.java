package colining.cn;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetriTest {

    @Test
    public void testCreate() {
        int size = 5;
        Petri petri = new Petri(size);
        assertEquals(size, petri.getSize());
        assertFalse(petri.isLiving(1, 1));//左上角
        assertFalse(petri.isLiving(1, size));//右上角
        assertFalse(petri.isLiving(size, 1));//左下角
        assertFalse(petri.isLiving(size, size));//右下角

    }

    @Test
    public void testCreateFromExisting() {
        Petri petri = new Petri(new int[][]
                {
                        {0, 0, 1},
                        {0, 0, 0},
                        {0, 0, 0}
                });
        assertFalse(petri.isLiving(1, 1));
        assertFalse(petri.isLiving(1, 2));
        assertTrue(petri.isLiving(1, 3));
    }

    @Test
    public void testSet() {
        int size = 5;
        Petri petri = new Petri(size);

        int row = 1;
        int column = 1;

        petri.setLiving(row, column);
        assertTrue(petri.isLiving(row, column));

        petri.setDead(row, column);
        assertFalse(petri.isLiving(row, column));

    }

//    @Test
//    public void testShouldLiveInNextTurn_becomeLiving() {
//        Petri petri = new Petri(new int[][]
//                {
//                        {1, 0, 1},
//                        {0, 1, 0},
//                        {0, 0, 0}
//                });
//
//        assertTrue(petri.shouldLiveNextTurn(1, 2));
//    }
}