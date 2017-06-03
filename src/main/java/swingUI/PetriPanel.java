package swingUI;

import game.Coordinate;
import game.Petri;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PetriPanel extends JPanel {
	private Map<Coordinate, Cell> cells;

	public PetriPanel(int size, ActionListener cellClickedListener) {
		this.setLayout(new GridLayout(size, size));
		cells = new HashMap<Coordinate, Cell>();
		fillGridWithCells(size, cellClickedListener);
		updateCellsToDisplay(PetriSingleton.petri);
	}

	private void fillGridWithCells(int gridSize, ActionListener cellClickedListener) {
		for (int row = 1; row <= gridSize; row++) {
			for (int column = 1; column <= gridSize; column++) {
				Cell cell = new Cell(row, column);
				cell.addActionListener(cellClickedListener);
				add(cell);
				cells.put(new Coordinate(row, column), cell);
			}
		}
	}

	void updateCellsToDisplay(Petri petri){
		for (int row = 1; row <= petri.getSize(); row++) {
			for (int column = 1; column <= petri.getSize(); column++) {
				cells.get(new Coordinate(row, column)).setLiving(petri.isLiving(row, column));
			}
		}
	}
}
