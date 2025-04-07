import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
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


    // Add more tests here!
    // Come up with varied cases
}
