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
}