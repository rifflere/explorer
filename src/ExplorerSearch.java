import java.util.ArrayList;
import java.util.Arrays;
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
        // Edge case checks
        if (island == null || island.length == 0 || island[0].length == 0) return 0;

        // Find start
        int[] start = findStart(island);

        // Create record of visited locations, set to all False by default
        // boolean[][] visited = new boolean[island.length][island[0].length];

        // Created a new class object (ExploredMap) to troubleshoot pass by reference not value
        ExploredMap visited = new ExploredMap(island.length, island[0].length);

        // recursively explore island, adding to validMoves
        int canReach = reachableArea(island, visited, start);

        return canReach;
    }

    public static int reachableArea(int[][] island, ExploredMap visited, int[] current) {
        // deconstruct current
        int row = current[0];
        int col = current[1];

        // If there's a record of this location, return; otherwise, add location to record
        if (visited.getRecord(row, col)) return 0;
        visited.setRecord(row, col, true);

        // Increment valid move counter
        int canReach = 1;

        List<int[]> moves = validMoves(island, current);

        for (var newLocation: moves) {
            // Recurse and add to validMoves
            canReach += reachableArea(island, visited, newLocation);
        }
        
        // return total number of valid moves
        return canReach;
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
        int[] falseStart = {-1, -1};
        boolean startFound = false;

        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {

                // This check may be unnecessary if guaranteed valid data, but I want to ensure that I am working with a valid map before I start processing it.
                if(island[r][c] > 3 || island[r][c] < 0) {
                    throw new IllegalArgumentException("Island contains invalid character(s)."); 
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

        if (Arrays.equals(start, falseStart)) {
            throw new IllegalArgumentException("Island contains no start.");
        }

        return start;
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
