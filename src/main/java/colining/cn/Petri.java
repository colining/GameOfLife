package colining.cn;/** * 翻译：Petri-培养皿 */public class Petri {    public static final int LIVING = 1;    //public static final int DEAD = 0;    private int[][] cells;//1：生；0：死    /**     * 创建方形培养皿     *     * @param size 边长     */    public Petri(int size) {        cells = new int[size][size];//Java语言中，数组元素默认初始化为0    }    public int getSize() {        return cells.length;    }    public boolean isLiving(int row, int column) {        return cells[row - 1][column - 1] == LIVING;    }}