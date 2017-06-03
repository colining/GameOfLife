package swingUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresetPetrisAndEvolveRatePanel extends JPanel implements ActionListener {
    private JButton resumeButton;
    private JButton pauseButton;
    private MultiGearedButton<Long> rateSwitcher;//切换进化速率（单位：毫秒）

    private static final long INTERVAL_VERY_FAST = 500;
    private static final long INTERVAL_FAST = 1000;
    private static final long INTERVAL_MEDIUM = 1500;
    private static final long INTERVAL_SLOW = 2000;

    private EvolveRateController controller;

    public PresetPetrisAndEvolveRatePanel(boolean isRunning) {
        this.setLayout(new GridLayout(0, 1));

        this.add(new JLabel("速率："));
        this.add(rateSwitcher = new MultiGearedButton<Long>(INTERVAL_SLOW, INTERVAL_MEDIUM, INTERVAL_FAST, INTERVAL_VERY_FAST));
        this.add(resumeButton = new JButton("开始"));
        this.add(pauseButton = new JButton("暂停"));

        resumeButton.addActionListener(this);
        pauseButton.addActionListener(this);
        rateSwitcher.addActionListener(this);

        if (isRunning) {
            resume();
        } else {
            pause();
        }
    }

    public void setEvolveRateController(EvolveRateController controller) {
        this.controller = controller;
    }

    public long getEvolveIntervalInMills() {
        return rateSwitcher.getValue();
    }

    public void actionPerformed(ActionEvent e) {
        if (controller == null) {
            return;
        }

        if (e.getSource() == resumeButton) {
            controller.resume();
            resume();
        } else if (e.getSource() == pauseButton) {
            controller.pause();
            pause();
        } else if (e.getSource() == rateSwitcher) {
            rateSwitcher.nextGear();
            controller.setInterval(rateSwitcher.getValue());
        }
    }

    private void pause() {
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(true);
    }

    private void resume() {
        pauseButton.setEnabled(true);
        resumeButton.setEnabled(false);
    }
}
