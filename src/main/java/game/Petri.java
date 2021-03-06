package game;

import java.util.Set;

/**
 * 翻译：Petri-培养皿
 */
public class Petri {
    public static final int LIVING = 1;
    public static final int DEAD = 0;

    private int[][] cells;//1：生；0：死

    /**
     * 创建方形培养皿
     *
     * @param size 边长
     */
    public Petri(int size) {
        cells = makeCells(size);
    }

    public Petri(int[][] existing) {
        cells = existing;
    }

    public void update(int[][] newCells){
        cells = newCells;
    }

    public void evolve() {
        int[][] nextGenerationCells = makeCells(getSize());

        for (int row = 1; row <= getSize(); row++) {
            for (int column = 1; column <= getSize(); column++) {
                if (shouldLiveNextGeneration(row, column)) {
                    setLiving(nextGenerationCells, row, column);
                }
            }
        }

        cells = nextGenerationCells;
    }

    public int getSize() {
        return cells.length;
    }

    public boolean isLiving(int row, int column) {
        return isLiving(cells, row, column);
    }

    public void setLiving(int row, int column) {
        setLiving(cells, row, column);
    }

    public void setDead(int row, int column) {
        setDead(cells, row, column);
    }

    public void reverse(int row, int column){
        if(isLiving(row, column)){
            setDead(row, column);
        }else{
            setLiving(row, column);
        }
    }

    public boolean shouldLiveNextGeneration(int row, int column) {
        if (countLivingNeighbours(row, column) == 3) {
            return true;
        } else if (countLivingNeighbours(row, column) == 2) {
            return isLiving(row, column);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof Petri)) {
            return false;
        }

        Petri anotherPetri = (Petri) another;
        for (int row = 1; row <= getSize(); row++) {
            for (int column = 1; column <= getSize(); column++) {
                if (!isLiving(row, column) == anotherPetri.isLiving(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countLivingNeighbours(int row, int column) {
        int result = 0;

        Set<Coordinate> neighbourPositions = new Coordinate(row, column).getSurroundings(getSize());
        for (Coordinate neighbour : neighbourPositions) {
            if (isLiving(neighbour.row, neighbour.column)) {
                result++;
            }
        }

        return result;
    }

    /**
     * 初始化培养皿，使得每个细胞都是死的
     *
     * @param petriSize 方形培养皿的边长
     * @return 数组，表示培养皿中的细胞
     */
    private int[][] makeCells(int petriSize) {
        return new int[petriSize][petriSize];//Java语言中，int数组的元素默认初始化为0
    }

    private boolean isLiving(int[][] cells, int row, int column) {
        return cells[row - 1][column - 1] == LIVING;
    }

    private void setLiving(int[][] cells, int row, int column) {
        cells[row - 1][column - 1] = LIVING;
    }

    private void setDead(int[][] cells, int row, int column) {
        cells[row - 1][column - 1] = DEAD;
    }
}

