package colining.cn;

import java.util.HashSet;
import java.util.Set;

public class Coordinate {
    public int row;
    public int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Set<Coordinate> getSurroundings(int size) {
        Set<Coordinate> results = new HashSet<Coordinate>();

        for (int row = this.row - 1; row <= this.row + 1; row++) {
            for (int column = this.column - 1; column <= this.column + 1; column++) {
                Coordinate neighbour = new Coordinate(row, column);
                if(neighbour.equals(this)){
                    continue;
                }
                results.add(neighbour);
            }
        }

        return results;
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof Coordinate)) {
            return false;
        }

        Coordinate anotherCoordinate = (Coordinate) another;
        return row == anotherCoordinate.row && column == anotherCoordinate.column;
    }

    @Override
    public int hashCode() {
        return new Integer(row * column).hashCode();
    }
}
