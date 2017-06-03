package main;


import game.Petri;
import org.omg.PortableInterceptor.INACTIVE;

public class GameOfLife {
    public static long updateIntervalInMills = 1000;

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

        FrameRateUserInterface frameRateUserInterface = new FrameRateUserInterface() {
            public void setUpdateInterval(long intervalInMills) {
                intervalInMills = intervalInMills;
            }

            public long getIntervalInMills() {
                return intervalInMills;
            }

            public int[][] getCells() {
                return new int[0][];
            }

            public void setCells(int[][] cells) {
                cells = cells;
            }
        };


        int[][] initialCells = frameRateUserInterface.getCells();

        Petri petri = new Petri(initialCells);
        while (true) {
            System.out.println("===============================");
            ui.display(petri);
            try {
                Thread.sleep(frameRateUserInterface.getIntervalInMills());
            } catch (InterruptedException e) {
                break;
            }
            petri.evolve();
        }
    }

}
