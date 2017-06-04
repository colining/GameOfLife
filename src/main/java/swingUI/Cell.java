package swingUI;

import javax.swing.JButton;
import java.awt.*;

public class Cell extends JButton {
    public static final String LIVING = "■";
    public static final String DEAD = "";

    public int row;
    public int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setLiving(boolean isLiving) {
        this.setText(getDisplayedState(isLiving));
        this.setBackground(getDisplayedColor(isLiving));
    }

    private String getDisplayedState(boolean isLiving) {
        return isLiving ? LIVING : DEAD;
    }

    private Color getDisplayedColor(boolean isLiving) {
        return isLiving ? Color.BLUE : Color.WHITE;
    }
}
