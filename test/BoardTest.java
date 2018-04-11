import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board makeBoard(int size) {
        return new Board(new int[size][size]);
    }

    @Test
    void output() {
        Board board = makeBoard(3);
        assertEquals("3\n" +
                " 0  0  0 \n" +
                " 0  0  0 \n" +
                " 0  0  0 \n", board.toString());
    }

    @Test
    void dimension() {
        Board board = makeBoard(3);
        assertEquals(3, board.dimension());
    }


    @Test
    void isGoal__false() {
        Board board = makeBoard(3);
        assertFalse(board.isGoal());
    }

    @Test
    void isGoal__true() {
        int[][] blocks = new int[3][3];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        Board board = new Board(blocks);
        assertTrue(board.isGoal());
    }

    @Test
    void equals__false() {
        Board board = makeBoard(3);
        assertFalse(board.equals(null));
        assertFalse(board.equals(makeBoard(2)));
        int[][] otherBlocks = new int[3][3];
        otherBlocks[0][0] = 3;
        assertFalse(board.equals(new Board(otherBlocks)));
    }

    @Test
    void equals__true() {
        Board board = makeBoard(3);
        assertFalse(board.equals(makeBoard(3)));
    }


}