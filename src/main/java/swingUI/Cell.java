package swingUI;

import javax.swing.JButton;

public class Cell extends JButton {
	public static final String LIVING = "■";
	public static final String DEAD = "";

	private int row;
	private int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void setLiving(boolean isLiving){
		this.setText(getDisplayedState(isLiving));
	}

	private String getDisplayedState(boolean isLiving){
		return isLiving ? LIVING : DEAD;
	}
}
