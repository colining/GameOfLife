package main;


import com.sun.org.apache.bcel.internal.generic.DDIV;
import game.Petri;

public class GameOfLife {

    public static void main(String[] args) {
        PetriUserInterface ui = new PetriUserInterface() {
            public static final char LIVING = '■';
            public static final char DEAD = '□';

            public void display(Petri petri) {
                for (int row = 1; row <= petri.getSize(); row++) {
                    for (int column = 1; column <= petri.getSize(); column++) {
                        System.out.print(petri.isLiving(row, column) ? LIVING : DEAD);
                    }
                    System.out.println();
                }
            }
        };

        int[][] initialCells = new int[][]{
                {0, 0,0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 1},
        };
        Petri petri = new Petri(initialCells);
        while (true) {
            System.out.println("===============================");
            ui.display(petri);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
            petri.evolve();
        }
    }

}
