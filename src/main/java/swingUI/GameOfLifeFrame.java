package swingUI;

import game.Petri;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.*;

public class GameOfLifeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 640;// 单位：像素
    private static final int DEFAULT_HEIGHT = 480;// 单位：像素

    private volatile long evolveIntervalInMills;

    private PetriPanel petriPanel;
    private UpdatePetriTask updatePetriTask;

    public GameOfLifeFrame(Petri petri, long evolveIntervalInMills) {
        makeUIComponents(petri);

        this.evolveIntervalInMills = evolveIntervalInMills;
        new Thread(updatePetriTask = new UpdatePetriTask(false)).start();
        updatePetriTask.resume();
    }

    private void makeUIComponents(Petri petri) {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(petriPanel = new PetriPanel(petri.getSize()), BorderLayout.CENTER);
        this.add(new EvolveRatePanel(), BorderLayout.EAST);

        this.setVisible(true);
    }

    private class UpdatePetriTask implements Runnable {
        private volatile boolean running;

        UpdatePetriTask(boolean running) {
            this.running = running;
        }

        void resume() {
            running = true;
        }

        void pause() {
            running = false;
        }

        public void run() {
            while (true) {
                petriPanel.updateCellsToDisplay(PetriSingleton.petri);

                try {
                    Thread.sleep(evolveIntervalInMills);
                } catch (InterruptedException e) {
                    continue;
                }

                if (running) {
                    PetriSingleton.petri.evolve();
                }
            }
        }
    }
}
