import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_allUnreachable() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,0,1},
            {1,1,1,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }


    @Test
    public void testReachableArea_someUnreachableMiddleStart() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    @Test
    public void testReachableArea_someUnreachableTopLeftStart() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(3, actual);
    }

    @Test
    public void testReachableArea_someUnreachableTopRightStart() {
        int[][] island = {
            {1,1,1,3,1,0},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(3, actual);
    }

    @Test
    public void testReachableArea_someUnreachableBottomLeftStart() {
        int[][] island = {
            {1,1,1,3,1,2},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {0,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(10, actual);
    }

    @Test
    public void testReachableArea_someUnreachableBottomRightStart() {
        int[][] island = {
            {1,1,1,3,1,2},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {2,1,1,2,1,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(3, actual);
    }

    @Test
    public void testReachableArea_oneReachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,3,0,3},
            {1,1,1,2,3,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    // findStart tests

    @Test 
    public void testFindStart_oneValidStartInMiddle() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = {3, 4};

        assertArrayEquals(expected, actual);
    }

    @Test 
    public void testFindStart_oneValidStartInFirstPosition() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };

        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = {0, 0};

        assertArrayEquals(expected, actual);
    }

    @Test 
    public void testFindStart_oneValidStartInLastPosition() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,0},
        };

        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = {4, 5};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindStart_noValidStart() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findStart(island);
        });
        assertEquals("Island contains no start.", exception.getMessage());
    };

    @Test
    public void testFindStart_multipleStarts() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,0,1,2,0,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findStart(island);
        });
        assertEquals("Island contains multiple starts.", exception.getMessage());
    };


    @Test
    public void testFindStart_containsInvalidCharacter() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,4,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findStart(island);
        });
        assertEquals("Island contains invalid character(s).", exception.getMessage());
    };

    // Test Valid Moves

    @Test
    public void testValidMoves_fourMoves() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,1,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {3, 4};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("2,4")); // up
        assertTrue(moveSet.contains("4,4")); // down
        assertTrue(moveSet.contains("3,3")); // left
        assertTrue(moveSet.contains("3,5")); // right

    }

    @Test
    public void testValidMoves_threeMoves() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {3, 4};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("4,4")); // down
        assertTrue(moveSet.contains("3,3")); // left
        assertTrue(moveSet.contains("3,5")); // right

    }

    @Test
    public void testValidMoves_twoMoves() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,3},
            {1,1,1,2,1,1},
        };

        int[] location = {3, 4};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("4,4")); // down
        assertTrue(moveSet.contains("3,3")); // left
    }

    @Test
    public void testValidMoves_oneMove() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,3},
            {1,1,1,2,3,1},
        };

        int[] location = {3, 4};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("3,3")); // left
    }

    @Test
    public void testValidMoves_noMoves() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,3,0,3},
            {1,1,1,2,3,1},
        };

        int[] location = {3, 4};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
  
        assertTrue(moves.isEmpty()); 
    }

    @Test
    public void testValidMoves_topLeftCorner() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);
  
        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1")); // right
    }

    @Test
    public void testValidMoves_topRightCorner() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {0, 5};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);
  
        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("0,4")); // left
        assertTrue(moveSet.contains("1,5")); // down
    }

    @Test
    public void testValidMoves_bottomLeftCorner() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {4, 0};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);
  
        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("4,1")); // right
    }

    @Test
    public void testValidMoves_bottomRightCorner() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };

        int[] location = {4, 5};
        List<int[]> moves = ExplorerSearch.validMoves(island, location);
        Set<String> moveSet = toSet(moves);
  
        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("3,5")); // up
        assertTrue(moveSet.contains("4,4")); // left
    }

    // This private helper method which converts a List<int[]> to a Set<String> is borrowed from the Salamander Search 
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
