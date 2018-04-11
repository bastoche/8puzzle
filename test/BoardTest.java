import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board makeEmptyBoard() {
        return makeEmptyBoard(3);
    }

    Board makeEmptyBoard(int size) {
        int[] elements = {};
        return makeBoard(size, elements);
    }

    Board makeGoalBoard() {
        int[] elements = {1, 2, 3, 4, 5, 6, 7, 8};
        return makeBoard(3, elements);
    }

    Board makeBoard(int size, int[] elements) {
        int[][] blocks = new int[size][size];
        for (int i = 0; i < elements.length; ++i) {
            blocks[i / 3][i % 3] = elements[i];
        }
        return new Board(blocks);
    }

    @Test
    void output() {
        Board board = makeEmptyBoard(3);
        assertEquals("3\n" +
                " 0  0  0 \n" +
                " 0  0  0 \n" +
                " 0  0  0 \n", board.toString());
    }

    @Test
    void dimension() {
        Board board = makeEmptyBoard(3);
        assertEquals(3, board.dimension());
    }


    @Test
    void isGoal__false() {
        Board board = makeEmptyBoard();
        assertFalse(board.isGoal());
    }

    @Test
    void isGoal__true() {
        Board board = makeGoalBoard();
        assertTrue(board.isGoal());
    }

    @Test
    void equals__false() {
        Board board = makeEmptyBoard(3);
        assertFalse(board.equals(null));
        assertFalse(board.equals(makeEmptyBoard(2)));
        int[][] otherBlocks = new int[3][3];
        otherBlocks[0][0] = 3;
        assertFalse(board.equals(new Board(otherBlocks)));
    }

    @Test
    void equals__true() {
        Board board = makeEmptyBoard(3);
        assertTrue(board.equals(makeEmptyBoard(3)));
    }

    @Test
    void hamming() {
        assertEquals(0, makeGoalBoard().hamming());

        int[] elements = {8, 1, 3, 4, 0, 2, 7, 6, 5};
        Board board = makeBoard(3, elements);
        assertEquals(5, board.hamming());
    }

    @Test
    void manhattan() {
        assertEquals(0, makeGoalBoard().manhattan());

        int[] elements = {8, 1, 3, 4, 0, 2, 7, 6, 5};
        Board board = makeBoard(3, elements);
        assertEquals(10, board.manhattan());
    }

    @Test
    void twin() {
        Board board = makeGoalBoard();
        assertFalse(board.twin().equals(makeEmptyBoard(3)));
        assertFalse(board.twin().equals(board));
        assertFalse(board.twin().isGoal());
    }

    private static <T> int size(Iterable<T> iterable) {
        int count = 0;
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    @Test
    void neighbors() {
        assertEquals(2, size(makeGoalBoard().neighbors()));

        int[] elements = {1, 2, 3, 4, 0, 5, 6, 7, 8};
        Board board = makeBoard(3, elements);
        assertEquals(4, size(board.neighbors()));
    }

}