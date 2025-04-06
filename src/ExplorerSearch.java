import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too

        int validMoves = 0;

        // Find start
        int[] start = findStart(island);

        // (if there is a start, validMoves += 1)

        // recursively explore island, adding to validMoves

        return validMoves;
    }

 
    /* Helper method to find starting position
     * Input: int[][] island
     * Output: int[] containing {r, c} where r = row of start, c = column of '0'
     * Throws IllegalArgumentException if there is no '0' in island
     * TODO: handle edge case where there is more than one '0' on island?
     */
    public static int[] findStart(int[][] island) {
        // Loop through island until '0' found, return int array of row, column coordinates
        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {
                if (island[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }
        throw new IllegalArgumentException("No starting location indicated.");
    }

    // Helper method to return valid moves
}
