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

 
    /* Helper method to find starting position.
     * Input: int[][] island
     * Output: int[] containing {r, c} where r = row of start, c = column of '0'
     * Throws IllegalArgumentException: 
     * - if there is no '0' in island
     * - if there is more than one '0' in island
     * - if any number in island is <0 or >3
     */
    public static int[] findStart(int[][] island) {
        // Loop through island until '0' found, return int array of row, column coordinates
        int[] start = {-1, -1};
        boolean startFound = false;

        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {

                if(island[r][c] > 3 || island[r][c] < 0) {
                    throw new IllegalArgumentException("Island contains invalid character(s)."); // Make this more descriptive
                }

                if (island[r][c] == 0) {
                    if (!startFound) {
                        start[0] = r;
                        start[1] = c;
                        startFound = true;
                    } else {
                        throw new IllegalArgumentException("Island contains multiple starts.");
                    }
                }
            }
        }

        if (!start.equals(new int[]{-1, -1})) {
            return start;
        } else {
            throw new IllegalArgumentException("Island contains no start.");
        }
    }

    // 

    /* Helper method to return valid moves
     * Input: int[][] island, int[] current location
     * Output: List<int[]> containing 0 - 4 row, column coordinates of possible moves.
     */
    public static List<int[]> validMoves(int[][] island, int[] currentLocation) {
        // Deconstruct current location into row, column variables
        int row = currentLocation[0];
        int col = currentLocation[1];

        List<int[]> moves = new ArrayList<>();

        // UP (row - 1)
        int upRow = row - 1;
        if (upRow >= 0 && island[upRow][col] == 1) {
            moves.add(new int[]{upRow, col});
        }

        // DOWN (row + 1)
        int downRow = row + 1;
        if (downRow < island.length && island[downRow][col] == 1) {
            moves.add(new int[]{downRow, col});
        }

        // LEFT (column - 1)
        int leftCol = col - 1;
        if (leftCol >= 0 && island[row][leftCol] == 1) {
            moves.add(new int[]{row, leftCol});
        }

        // RIGHT (column + 1)
        int rightCol = col + 1;
        if (rightCol < island[0].length && island[row][rightCol] == 1) {
            moves.add(new int[]{row, rightCol});
        }

        return moves;
    }

}
