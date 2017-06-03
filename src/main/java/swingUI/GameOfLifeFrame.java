package swingUI;

import game.Petri;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameOfLifeFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WIDTH = 640;// 单位：像素
    private static final int DEFAULT_HEIGHT = 480;// 单位：像素

    private PetriPanel petriPanel;
    private PresetPetriAndEvolveRatePanel presetPetriAndEvolveRatePanel;

    private UpdatePetriTask updatePetriTask;

    private Petri petri;

    public GameOfLifeFrame(Petri petri, boolean isRunning, PresetPetri... presets) {
        this.petri = petri;
        makeUIComponents(petri, isRunning, presets);
        new Thread(updatePetriTask = new UpdatePetriTask(presetPetriAndEvolveRatePanel.getEvolveIntervalInMills(), isRunning)).start();
        presetPetriAndEvolveRatePanel.setEvolveRateController(updatePetriTask);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (updatePetriTask.isRunning()) {
            return;//确保同时只有1个线程访问{@link PetriSingleton#petri}
        }

        if (e.getSource() instanceof Cell) {
            Cell cell = (Cell) e.getSource();
            petri.reverse(cell.row, cell.column);
            petriPanel.updateCellsToDisplay(petri);
        } else if (e.getSource() instanceof PresetPetriButton) {
            PresetPetriButton button = (PresetPetriButton) e.getSource();
            button.update(petri);
        }

    }

    private void makeUIComponents(Petri petri, boolean isRunning, PresetPetri... presets) {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(petriPanel = new PetriPanel(petri.getSize(), this), BorderLayout.CENTER);
        this.add(presetPetriAndEvolveRatePanel = new PresetPetriAndEvolveRatePanel(
                isRunning, this, presets), BorderLayout.EAST);

        this.setVisible(true);
    }

    private class UpdatePetriTask implements Runnable, EvolveRateController {
        private volatile boolean running;
        private volatile long evolveIntervalInMills;

        UpdatePetriTask(long evolveIntervalInMills, boolean running) {
            this.running = running;
            this.evolveIntervalInMills = evolveIntervalInMills;
        }

        boolean isRunning() {
            return running;
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
        public void setInterval(long intervalInMills) {
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
