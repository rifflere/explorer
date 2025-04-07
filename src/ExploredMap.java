public class ExploredMap {
    // This class holds a boolean map of explored values
    boolean[][] explored;
    
    public ExploredMap(int rows, int columns) {
        explored = new boolean[rows][columns];
    }

    public boolean getRecord(int row, int column) {
        return explored[row][column];
    }

    public void setRecord(int row, int column, boolean isExplored) {
        explored[row][column] = isExplored;
    }
}
