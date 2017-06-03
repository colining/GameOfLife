package colining.cn;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PetriTest {
    private int size = 5;
    private Petri petri;

    @Before
    public void setUp() {
        petri = new Petri(size);
    }

    @Test
    public void testCreate() {
        assertEquals(size, petri.getSize());
        assertFalse(petri.isLiving(1, 1));//左上角
        assertFalse(petri.isLiving(1, size));//右上角
        assertFalse(petri.isLiving(size, 1));//左下角
        assertFalse(petri.isLiving(size, size));//右下角

    }

    @Test
    public void testSet() {
        int row = 1;
        int column = 1;

        petri.setLiving(row, column);
        assertTrue(petri.isLiving(row, column));

        petri.setDead(row, column);
        assertFalse(petri.isLiving(row, column));

    }
}