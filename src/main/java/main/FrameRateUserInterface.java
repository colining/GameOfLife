package main;

/**
 * Created by asus on 2017/6/3.
 */
public interface FrameRateUserInterface {
    public long intervalInMills = 0;
    public void setUpdateInterval(long intervalInMills);
    public long getIntervalInMills();
    public int[][] cells = new int[0][];
    public int[][] getCells();
    public void setCells(int[][] cells);
}
