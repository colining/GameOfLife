package swingUI;

import game.Petri;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameOfLifeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 640;// 单位：像素
    private static final int DEFAULT_HEIGHT = 480;// 单位：像素

    private PetriPanel petriPanel;
    private EvolveRatePanel evolveRatePanel;
    private UpdatePetriTask updatePetriTask;

    public GameOfLifeFrame(Petri petri) {
        makeUIComponents(petri);

        new Thread(updatePetriTask = new UpdatePetriTask(evolveRatePanel.getEvolveIntervalInMills(), false)).start();
        updatePetriTask.resume();
        evolveRatePanel.setEvolveRateController(updatePetriTask);
    }

    private void makeUIComponents(Petri petri) {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(petriPanel = new PetriPanel(petri.getSize()), BorderLayout.CENTER);
        this.add(evolveRatePanel = new EvolveRatePanel(), BorderLayout.EAST);

        this.setVisible(true);
    }

    private class UpdatePetriTask implements Runnable, EvolveRateController {
        private volatile boolean running;
        private volatile long evolveIntervalInMills;

        UpdatePetriTask(long evolveIntervalInMills, boolean running) {
            this.running = running;
            this.evolveIntervalInMills = evolveIntervalInMills;
        }

        @Override
        public void resume() {
            running = true;
        }

        @Override
        public void pause() {
            running = false;
        }

        @Override
        public void setInterval(long intervalInMills){
            this.evolveIntervalInMills = intervalInMills;
            Thread.currentThread().interrupt();
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
