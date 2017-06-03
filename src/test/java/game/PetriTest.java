package game;

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
    public void testEvolve_allDie() {
        Petri petri = new Petri(new int[][]
                {
                        {0, 0, 1},
                        {0, 0, 1},
                        {0, 0, 0}
                });
        petri.evolve();
        assertEquals(new Petri(new int[][]
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }), petri);
    }

    @Test
    public void testEvolve_competeToDeath() {
        Petri petri = new Petri(new int[][]
                {
                        {0, 0, 1, 1},
                        {0, 0, 1, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 0}
                });
        petri.evolve();
        assertEquals(new Petri(new int[][]
                {
                        {0, 0, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 1, 1},
                        {0, 0, 0, 0}
                }), petri);
    }

    @Test
    public void testEvolve_symbiotic() {//翻译：symbiotic-共生
        Petri petri = new Petri(new int[][]
                {
                        {0, 1, 1},
                        {0, 1, 1},
                        {0, 0, 0}
                });
        petri.evolve();
        assertEquals(new Petri(new int[][]
                {
                        {0, 1, 1},
                        {0, 1, 1},
                        {0, 0, 0}
                }), petri);
    }

    @Test
    public void testEvolve_proliferation() {//翻译：proliferation-增殖
        Petri petri = new Petri(new int[][]
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {0, 1, 0}
                });
        petri.evolve();
        assertEquals(new Petri(new int[][]
                {
                        {0, 0, 0},
                        {0, 1, 1},
                        {0, 0, 0}
                }), petri);
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

    @Test
    public void testShouldLiveInNextGeneration_becomeLiving() {
        Petri petri = new Petri(new int[][]
                {
                        {1, 0, 1},
                        {0, 1, 0},
                        {0, 0, 0}
                });

        assertTrue(petri.shouldLiveNextGeneration(1, 2));
    }

    @Test
    public void testShouldLiveInNextGeneration_stayLiving() {
        Petri petri = new Petri(new int[][]
                {
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 0, 0}
                });

        assertTrue(petri.shouldLiveNextGeneration(1, 2));
    }

    @Test
    public void testShouldLiveInNextGeneration_stayDead() {
        Petri petri = new Petri(new int[][]
                {
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}
                });

        assertFalse(petri.shouldLiveNextGeneration(1, 2));
    }

    @Test
    public void testShouldLiveInNextGeneration_becomeDead() {
        Petri petri = new Petri(new int[][]
                {
                        {1, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                });

        assertFalse(petri.shouldLiveNextGeneration(1, 2));
    }
}