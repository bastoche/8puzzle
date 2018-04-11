import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void output() {
        int[][] blocks = new int[3][3];
        Board board = new Board(blocks);
        assertEquals("3\n" +
                " 0  0  0 \n" +
                " 0  0  0 \n" +
                " 0  0  0 \n", board.toString());
    }

}